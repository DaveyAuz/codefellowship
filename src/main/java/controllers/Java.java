package controllers;

package com.codefellowship.controllers;

import com.codefellowship.models.CodeUser;
import com.codefellowship.repositories.CodeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class CodeUserController {

    @Autowired
    CodeUserRepository codeUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView postSignup(String username, String password, String nickname) {
        CodeUser codeUser = new CodeUser();
        codeUser.setUsername(username);
        codeUser.setNickname(nickname);
        String encryptedPassword = passwordEncoder.encode(password);
        codeUser.setPassword(encryptedPassword);
        codeUserRepository.save(codeUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    public void authWithHttpServletRequest(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Error while logging in.");
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String getIndexPage(Model m, Principal p) {

        System.out.println("Principal " + p);

        if (p != null) {
            String username = p.getName();
            CodeUser codeUser = codeUserRepository.findByUsername(username);

            m.addAttribute("username", username);
            m.addAttribute("nickname", codeUser.getNickname());
        }
        return "index.html";
    }
}

