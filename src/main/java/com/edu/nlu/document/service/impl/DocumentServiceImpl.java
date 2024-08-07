package com.edu.nlu.document.service.impl;

import com.edu.nlu.document.model.Document;
import com.edu.nlu.document.repository.DocumentRepository;
import com.edu.nlu.document.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;


    @Override
    public List<Document> getAllDocumentsByUsername(String username) {
        return documentRepository.findAll();
    }
}
