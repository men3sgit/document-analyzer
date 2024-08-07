package com.edu.nlu.document.service;

import com.edu.nlu.document.model.Document;

import java.util.List;

public interface DocumentService {

    List<Document> getAllDocumentsByUsername(String username);

}
