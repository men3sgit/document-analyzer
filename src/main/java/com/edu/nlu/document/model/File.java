package com.edu.nlu.document.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, length = 1024)
    private String name;

    private String path;

    private long size;

    private String extension;
}