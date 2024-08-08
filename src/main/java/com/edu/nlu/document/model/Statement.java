package com.edu.nlu.document.model;

import com.edu.nlu.document.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "statements")
@Entity
public class Statement {
    @Id
    @GeneratedValue
    private Long id;

    private Long documentId;

    private String note;

    private Long userId;

    private Status status;
}
