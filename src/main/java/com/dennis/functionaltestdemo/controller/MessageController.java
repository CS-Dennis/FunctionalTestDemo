package com.dennis.functionaltestdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class MessageController {

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage() {
        // Simulate sending a message
        String responseMessage = "Message sent successfully!";


        return ResponseEntity.ok(responseMessage);
    }
}
