package com.edu.nlu.document.controller;

import com.edu.nlu.document.model.Document;
import com.edu.nlu.document.model.File;
import com.edu.nlu.document.model.Statement;
import com.edu.nlu.document.model.User;
import com.edu.nlu.document.model.Department;
import com.edu.nlu.document.payload.DocumentDetails;
import com.edu.nlu.document.payload.DocumentForm;
import com.edu.nlu.document.payload.UserDetails;
import com.edu.nlu.document.service.DepartmentService;
import com.edu.nlu.document.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;
    private final DepartmentService departmentService;

    @GetMapping
    public String showUserList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping(path = "/data")
    public @ResponseBody List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/chanhvanphong")
    public @ResponseBody List<UserDetails> getChanhvanphong() {
        List<User> users = userService.findAll();
        List<UserDetails> usersDetails = new ArrayList<>();
        for (User user : users) {
            UserDetails userDetails = new UserDetails();
            System.out.println(user.toString());
            Department department = departmentService.getDepartmentById(user.getDepartmentId());
            System.out.println(department.toString());
            userDetails.setDepartment(department);
            userDetails.setUser(user);
            usersDetails.add(userDetails);
        }
        return usersDetails;
    }

    @GetMapping(path = "/bangiamdoc")
    public @ResponseBody List<UserDetails> getBangiamdoc() {
        List<User> users = userService.findAll();
        List<UserDetails> usersDetails = new ArrayList<>();
        for (User user : users) {
            UserDetails userDetails = new UserDetails();
            System.out.println(user.toString());
            Department department = departmentService.getDepartmentById(user.getDepartmentId());
            System.out.println(department.toString());
            userDetails.setDepartment(department);
            userDetails.setUser(user);
            usersDetails.add(userDetails);
        }
        return usersDetails;
    }

}
