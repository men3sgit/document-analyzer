package com.edu.nlu.document.controller;

import com.edu.nlu.document.model.User;
import com.edu.nlu.document.payload.DocumentForm;
import com.edu.nlu.document.service.DocumentService;
import com.edu.nlu.document.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DashboardController {
    private final UserService userService;
    private final DocumentService documentService;

    @GetMapping(path = {"/dashboard", "", "/"})
    public String showHomePage(Model model) {
        model.addAttribute("vanbans", documentService.getAllDocumentsByUsername(User.NO_NAME));
        return "dashboard";
    }


    @GetMapping(path = "/xulyvanban")
    public String showXuLyVanBan(Model model) {

        return "tiepnhanvanbanden";
    }

    @PostMapping(path = "/xulyvanban")
    public String showXuLyVanBan(@Valid DocumentForm documentForm, Model model) {
        log.info(documentForm.toString());
        return "tiepnhanvanbanden";
    }
}