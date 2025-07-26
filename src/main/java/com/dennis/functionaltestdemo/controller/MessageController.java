package com.dennis.functionaltestdemo.controller;

import com.dennis.functionaltestdemo.entity.Book;
import com.dennis.functionaltestdemo.repo.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api")
public class MessageController {
    private final BookRepository bookRepository;
    private final KafkaTemplate<String, Book> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void setup() {
        objectMapper.writerWithDefaultPrettyPrinter();
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Book book) throws JsonProcessingException {
        // Simulate sending a message

        System.out.println("Sending message... ");
        String responseMessage = "Message sent successfully!";

        this.kafkaTemplate.send("book-topic", UUID.randomUUID().toString(), book);

        return ResponseEntity.ok(objectMapper.writeValueAsString(book));
    }

    @GetMapping("/test")
    public ResponseEntity<Book> testMessage() {
        Book newBook = this.bookRepository.save(Book.builder()
                .bookName("Functional Test Book")
                .author("John Doe")
                .SN(UUID.randomUUID().toString())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build());

        return ResponseEntity.ok(newBook);
    }
}
