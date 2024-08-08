package com.edu.nlu.document.service.impl;

import com.edu.nlu.document.enums.Role;
import com.edu.nlu.document.model.User;
import com.edu.nlu.document.repository.UserRepository;
import com.edu.nlu.document.service.CommonService;
import com.edu.nlu.document.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CommonService commonService;


    @Override
    public List<User> findAll() {
        Role role = commonService.getCurrentUserRole();
        Role roleMustFind = switch (role){
            case VAN_THU -> Role.CHANH_VAN_PHONG;
            case CHANH_VAN_PHONG -> Role.BAN_GIAM_DOC;
            case BAN_GIAM_DOC, CHUYEN_VIEN -> Role.CHUYEN_VIEN;
        };
        return userRepository.findAllByRole(roleMustFind.name());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
