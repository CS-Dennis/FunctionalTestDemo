package com.dennis.functionaltestdemo.services;

import com.dennis.functionaltestdemo.entity.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
@Configuration
public class KafkaService {
    private final ObjectMapper objectMapper;

   @KafkaListener(
           containerFactory = "kafkaListenerContainerFactory",
              topics = "book-topic"
   )
    public void listen(Book book) throws JsonProcessingException {
        // Process the incoming message
        System.out.println("Received book:" );
       System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book));
    }

    // Additional methods for producing messages can be added here
}
