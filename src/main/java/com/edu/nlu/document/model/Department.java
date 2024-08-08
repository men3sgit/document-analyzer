package com.edu.nlu.document.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue
    private Long id;

    private String naturalId;

    private String name;

    private Long hostId; // userId
}
