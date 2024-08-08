package com.edu.nlu.document.service.impl;

import com.edu.nlu.document.mapper.DocumentMapper;
import com.edu.nlu.document.model.Document;
import com.edu.nlu.document.model.File;
import com.edu.nlu.document.payload.DocumentForm;
import com.edu.nlu.document.repository.DocumentRepository;
import com.edu.nlu.document.repository.FileRepository;
import com.edu.nlu.document.service.DocumentService;
import com.edu.nlu.document.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final FileRepository fileRepository;

    @Override
    public List<Document> getAllDocumentsByUsername(String username) {
//        select doc.* from document doc, statement sta, user u where u.id = statement.userID and statement.idDoc = doc.id
        return documentRepository.findAll();
    }

    @Override
    public void addNewDocument(DocumentForm documentForm) {
        var storedDocument = documentRepository.save(documentMapper.sourceToDestination(documentForm));
        List<MultipartFile> files = documentForm.getAttachedFiles();

        List<File> list = files.stream().map(multipartFile -> File.builder()
                .name(multipartFile.getOriginalFilename())
                .path("/assets/files/" + multipartFile.getOriginalFilename())
                .size(multipartFile.getSize())
                .documentId(storedDocument.getId())
                .extension(FileUtil.getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename())))
                .build()).toList();
        fileRepository.saveAll(list);

    }

    @Override
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Document not found"));
    }
}
