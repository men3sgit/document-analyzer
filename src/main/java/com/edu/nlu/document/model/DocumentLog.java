package com.edu.nlu.document.model;

import com.edu.nlu.document.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "document_logs")
@Entity
public class DocumentLog {

    @Id
    @GeneratedValue
    private Long id;

    private Status status;

    private Long createdBy;

    private LocalDateTime createdDate = LocalDateTime.now();

    private Long documentId;


}
