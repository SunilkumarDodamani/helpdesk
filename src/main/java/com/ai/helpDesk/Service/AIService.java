package com.ai.helpDesk.Service;

import com.ai.helpDesk.tools.TicketDatabaseTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient openAi;
    private final ChatClient gemini;
    private final TicketDatabaseTool ticketDatabaseTool;
    private long lastCallTime=0;
    private static final long coolDown_Ms=2000;

    @Value("classpath:helpdesk-system.st")
    private Resource systemPromptResource;

    public AIService(ChatClient openAi,
                     ChatClient gemini,
                     TicketDatabaseTool ticketDatabaseTool) {
        this.openAi = openAi;
        this.gemini = gemini;
        this.ticketDatabaseTool = ticketDatabaseTool;
    }

    public synchronized String askOpenAi(String query) {

        return openAi
                .prompt()
                .user(query)
                .call()
                .content();
    }

    public synchronized String askGemini(String query) {
        try {
            long now=System.currentTimeMillis();
            long diff=now-lastCallTime;
            if(diff<coolDown_Ms){
                Thread.sleep(coolDown_Ms-diff);
            }
            lastCallTime=System.currentTimeMillis();
            return gemini
                    .prompt()
                    .tools(ticketDatabaseTool)
                    .system(systemPromptResource)
                    .user(query)
                    .call()
                    .content();
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("429")) {
                return "⚠️  receiving too many requests. Please try again shortly.";
            }
            throw new RuntimeException("Failed to call Gemini: " + e.getMessage());
        }
    }

}
