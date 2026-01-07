package com.ai.helpDesk.controller;

import com.ai.helpDesk.Service.AIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/ai")
public class AiController {

   private final AIService aiService;
   private final Logger logger= LoggerFactory.getLogger(AiController.class);

    public AiController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/openai")
    public ResponseEntity<String> askOpenAI(@RequestParam String prompt) {
        return ResponseEntity.ok(aiService.askOpenAi(prompt));
    }


    @PostMapping("/gemini")
    public ResponseEntity<String> askGemini(@RequestBody String prompt) {
        logger.info("Controller received Gemini request: {}", prompt);
        return ResponseEntity.ok(aiService.askGemini(prompt));
    }

    @RequestMapping(value = "" ,method=RequestMethod.HEAD)
    public ResponseEntity<?> getHeader(){
        return ResponseEntity.ok().build();
    }



}
