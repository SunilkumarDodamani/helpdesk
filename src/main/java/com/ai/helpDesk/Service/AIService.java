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

    @Value("classpath:helpdesk-system.st")
    private Resource systemPromptResource;

    public AIService(ChatClient openAi,
                     ChatClient gemini,
                     TicketDatabaseTool ticketDatabaseTool) {
        this.openAi = openAi;
        this.gemini = gemini;
        this.ticketDatabaseTool = ticketDatabaseTool;
    }

    public String askOpenAi(String query) {
        return openAi
                .prompt()
                .user(query)
                .call()
                .content();
    }

    public String askGemini(String query) {
        try {
            return gemini
                    .prompt()
                    .tools(ticketDatabaseTool)
                    .system(systemPromptResource)
                    .user(user -> user.text(query))
                    .call()
                    .content();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
