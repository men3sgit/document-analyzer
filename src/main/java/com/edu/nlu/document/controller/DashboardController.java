package com.edu.nlu.document.controller;

import com.edu.nlu.document.enums.Role;
import com.edu.nlu.document.model.Document;
import com.edu.nlu.document.model.Statement;
import com.edu.nlu.document.model.StatementDocumentWrapper;
import com.edu.nlu.document.service.DocumentService;
import com.edu.nlu.document.service.StatementService;
import com.edu.nlu.document.service.UserService;
import com.edu.nlu.document.service.CommonService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DashboardController {
    private final DocumentService documentService;
    private final UserService userService;
    private final StatementService statementService;
    private final CommonService commonService;


    @GetMapping(path = {"/dashboard", "", "/"})
    public String showHomePage(HttpServletRequest request, Principal principal, Model model) {
        List<Document> documents = documentService.getAllDocumentsByCurrentUser();
        List<Statement> statements = statementService.getStatementsByCurrentUser();

        List<StatementDocumentWrapper> wrappedList = new ArrayList<>();
        for (int i = 0; i < documents.size(); i++) {
            wrappedList.add(new StatementDocumentWrapper(documents.get(i), statements.get(i)));
        }
        model.addAttribute("vanbans", wrappedList);

        model.addAttribute("users", userService.findAll());
        String role = commonService.getCurrentUserRole().name();
        return getDashboardByRole(role);
    }


    @GetMapping(path = "/xulyvanban")
    public String showXuLyVanBan(Model model) {
        model.addAttribute("users", userService.findAll());
        return "tiepnhanvanbanden";
    }


    private String getDashboardByRole(String role) {
        return switch (Role.valueOf(role)) {
            case VAN_THU -> "dashboard-vanthu";
            case CHANH_VAN_PHONG -> "dashboard-chanhvanphong";
            case BAN_GIAM_DOC -> "dashboard-bangiamdoc";
            case CHUYEN_VIEN -> "dashboard-chuyenvien";
        };
    }
}