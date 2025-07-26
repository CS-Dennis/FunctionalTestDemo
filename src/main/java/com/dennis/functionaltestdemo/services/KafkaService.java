package com.dennis.functionaltestdemo.services;

import com.dennis.functionaltestdemo.entity.Book;
import com.dennis.functionaltestdemo.repo.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaService {
    private final ObjectMapper objectMapper;
    private final BookRepository bookRepository;

   @KafkaListener(
           containerFactory = "kafkaListenerContainerFactory",
              topics = "book-topic"
   )
    public void listen(Book book) throws JsonProcessingException {
        // Process the incoming message

       log.info("Received book: {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book));
       Book newBook = Book.builder()
               .bookName(book.getBookName())
               .author(book.getAuthor())
               .SN(book.getSN())
               .createdAt(book.getCreatedAt())
               .updatedAt(book.getUpdatedAt())
               .build();
       bookRepository.save(newBook);
       log.info("Book saved.");
    }

    // Additional methods for producing messages can be added here
}
