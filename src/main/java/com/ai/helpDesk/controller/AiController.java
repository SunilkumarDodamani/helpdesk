package com.ai.helpDesk.controller;

import com.ai.helpDesk.Service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://691c05b7c1411bb4bc9c372f--sunil-helpdesk-ai.netlify.app")
@RestController
@RequestMapping("/api/v1/ai")
public class AiController {

   private final AIService aiService;

    public AiController(AIService aiService) {
        this.aiService = aiService;
    }
    @PostMapping("/openai")
    public ResponseEntity<String> askOpenAI(@RequestParam String prompt) {
        return ResponseEntity.ok(aiService.askOpenAi(prompt));
    }

    @PostMapping("/gemini")
    public ResponseEntity<String> askGemini(@RequestBody String prompt) {
        return ResponseEntity.ok(aiService.askGemini(prompt));
    }

}
