package com.edu.nlu.document.controller;

import com.edu.nlu.document.mapper.DocumentMapper;
import com.edu.nlu.document.model.Document;
import com.edu.nlu.document.model.File;
import com.edu.nlu.document.model.User;
import com.edu.nlu.document.payload.DocumentDetails;
import com.edu.nlu.document.payload.DocumentForm;
import com.edu.nlu.document.service.DocumentService;
import com.edu.nlu.document.service.FileService;
import com.edu.nlu.document.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/documents")
public class DocumentController {
    private final DocumentMapper documentMapper;
    private final UserService userService;
    private final DocumentService documentService;
    private final FileService fileService;

    @GetMapping
    public String showUserList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }


    @GetMapping(path = "/{id}")
    public @ResponseBody DocumentDetails showDocumentDetails(@PathVariable(name = "id") Long id, Model model) {
        Document document = documentService.getDocumentById(id);
        DocumentForm documentForm = documentMapper.destinationToSource(document);
        List<File> files = fileService.getAllFileByDocumentId(id);
        DocumentDetails documentDetails =new DocumentDetails();
        documentDetails.setDocumentId(document.getId());
        documentDetails.setDocumentForm(documentForm);
        documentDetails.setFiles(files);
        return documentDetails;
    }


}