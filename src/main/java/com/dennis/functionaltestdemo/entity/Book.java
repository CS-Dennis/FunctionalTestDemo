package com.dennis.functionaltestdemo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "book_id")
    private Long bookId;

    @JsonProperty(value = "book_name")
    private String bookName;

    private String author;

    private String SN;

    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
}
