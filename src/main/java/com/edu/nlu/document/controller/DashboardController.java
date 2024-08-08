package com.edu.nlu.document.controller;

import com.edu.nlu.document.enums.Role;
import com.edu.nlu.document.model.User;
import com.edu.nlu.document.payload.DocumentForm;
import com.edu.nlu.document.service.DocumentService;
import com.edu.nlu.document.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DashboardController {
    private final UserDetailsService userDetailsService;
    private final DocumentService documentService;


    @GetMapping(path = {"/dashboard", "", "/"})
    public String showHomePage(Principal principal, Model model) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("vanbans", documentService.getAllDocumentsByUsername(User.NO_NAME));

        String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
        return getDashboardByRole(role);
    }


    @GetMapping(path = "/xulyvanban")
    public String showXuLyVanBan(Model model) {

        return "tiepnhanvanbanden";
    }

    @PostMapping(path = "/xulyvanban")
    public String showXuLyVanBan(@Valid @ModelAttribute DocumentForm documentForm, Model model) {
        log.info(documentForm.toString());
        documentForm.getAttachedFiles()
                .stream()
                .map(MultipartFile::getOriginalFilename)
                .forEach(log::info);
        documentService.addNewDocument(documentForm);
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