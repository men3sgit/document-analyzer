package com.edu.nlu.document.controller;

import com.edu.nlu.document.enums.Status;
import com.edu.nlu.document.mapper.DocumentMapper;
import com.edu.nlu.document.model.Document;
import com.edu.nlu.document.model.File;
import com.edu.nlu.document.model.Statement;
import com.edu.nlu.document.model.User;
import com.edu.nlu.document.payload.DocumentDetails;
import com.edu.nlu.document.payload.DocumentForm;
import com.edu.nlu.document.payload.DocumentForward;
import com.edu.nlu.document.service.DocumentService;
import com.edu.nlu.document.service.FileService;
import com.edu.nlu.document.service.StatementService;
import com.edu.nlu.document.service.UserService;
import com.edu.nlu.document.service.CommonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/documents")
public class DocumentController {
    private final DocumentMapper documentMapper;
    private final UserService userService;
    private final DocumentService documentService;
    private final FileService fileService;
    private final StatementService statementService;
    private final CommonService commonService;

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
        Statement statement = statementService.getStatement(commonService.getCurrentUserId(), document.getId());

        DocumentDetails documentDetails = new DocumentDetails();
        documentDetails.setDocumentId(document.getId());
        documentDetails.setDocumentForm(documentForm);
        documentDetails.setFiles(files);
        documentDetails.setStatement(statement);

        return documentDetails;
    }

    @PostMapping(path = "/xulyvanban")
    public String showXuLyVanBan(@Valid @ModelAttribute DocumentForm documentForm, Model model) {
        log.info(documentForm.toString());
        documentForm.getAttachedFiles()
                .stream()
                .map(MultipartFile::getOriginalFilename)
                .forEach(log::info);
        documentService.addNewDocument(documentForm);
        model.addAttribute("users", userService.findAll());
        return "tiepnhanvanbanden";
    }

    @PostMapping(path = "/chuyenvanban")
    public String showChuyenVanBan(@Valid @ModelAttribute DocumentForward documentForward, Model model) {
        log.info(documentForward.toString());
        documentForward.getReceivedUsers().forEach(userId -> {
            Statement newStatement = new Statement();
            newStatement.setDocumentId(documentForward.getDocumentId());
            newStatement.setNote(documentForward.getContent());
            newStatement.setUserId(userId);
            if (Objects.equals(userId, documentForward.getMainReceivedUser())) {
                newStatement.setStatus(Status.SENT);
            } else {
                newStatement.setStatus(Status.RECEIVED);
            }
            statementService.createStatement(newStatement);

        });

        Statement storedStatement = statementService.getStatement(commonService.getCurrentUserId(), documentForward.getDocumentId());
        storedStatement.setStatus(Status.FORWARDED);
        statementService.updateStatement(storedStatement);
        return "redirect:/dashboard";
    }

    @PostMapping(path = "/luuvachuyenvanban")
    public String showLuuVaChuyenVanBan(@Valid @ModelAttribute DocumentForward documentForward,
                                        @ModelAttribute DocumentForm documentForm,
                                        Model model) {
        var document = documentService.addNewDocument(documentForm);

        documentForward.getReceivedUsers().forEach(userId -> {
            Statement newStatement = new Statement();
            newStatement.setDocumentId(document.getId());
            newStatement.setNote(documentForward.getContent());
            newStatement.setUserId(userId);
            if (Objects.equals(userId, documentForward.getMainReceivedUser())) {
                newStatement.setStatus(Status.SENT);
            } else {
                newStatement.setStatus(Status.RECEIVED);
            }
            statementService.createStatement(newStatement);

        });

        Statement storedStatement = statementService.getStatement(commonService.getCurrentUserId(), document.getId());
        storedStatement.setStatus(Status.FORWARDED);
        statementService.updateStatement(storedStatement);
        return "redirect:/dashboard";
    }

}