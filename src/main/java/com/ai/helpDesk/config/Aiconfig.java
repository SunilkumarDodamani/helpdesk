package com.ai.helpDesk.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Aiconfig {

    @Bean(name = "openAi")
    public ChatClient openAiChatClient(OpenAiChatModel model) {
        return ChatClient.builder(model)
                .defaultSystem("summerize the response within 200 words")
                .build();
    }

    @Bean(name="gemini")
    public ChatClient geminiChatClient(GoogleGenAiChatModel model, ChatMemory memory){
        return ChatClient.builder(model)

                .defaultSystem("give summerized answer within 200 words")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build()

                )
                .build();
    }

}
