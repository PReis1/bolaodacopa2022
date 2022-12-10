package com.time7.bolaodacopa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.time7.bolaodacopa.models.UserModel;
import com.time7.bolaodacopa.models.UserRepository;
import com.time7.bolaodacopa.models.AdminModel;
import com.time7.bolaodacopa.models.AdminRepository;

@Controller
public class AuthController {

    private Boolean isAuthenticated;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    public AuthController() {
        this.isAuthenticated = false;
    }

    @GetMapping(value = "/teste")
    public String teste(Model model) {
        model.addAttribute("page", "dashboard");
        return "index";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        if (this.isAuthenticated) {
            model.addAttribute("page", "dashboard");
            return "index";
        } else {
            return "login";
        }
    }

    @GetMapping(value = "/register")
    public String register(UserModel userModel) {
        return "register";
    }

    @PostMapping(value = "/adduser")
    public String registerUser(@Validated UserModel user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/register";
        }
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login() {
        if (this.isAuthenticated) {
            return "index";
        } else {
            return "login";
        }
    }
    
    @GetMapping(value = "/admin")
    public String admin() {
        if (this.isAuthenticated) {
            return "index";
        } else {
            return "admin";
        }
    }
    
    @PostMapping(value = "/logarAdmin")
    public String logarAdmin(@Validated AdminModel admin, AdminModel password, Model model) {
        AdminModel adminLogin = this.adminRepository.Login(admin.getEmail(), admin.getPassword());
        if (adminLogin != null) {
            this.isAuthenticated = true;
            model.addAttribute("adminName", admin.getNome());
            return "redirect:/selecoes";
        }
        model.addAttribute("error", "Usuário ou senha inválidos!");
        return "redirect:/admin";
    }

    @PostMapping(value = "/logar")
    public String logar(@Validated UserModel user, UserModel password, Model model) {
        UserModel userLogin = this.userRepository.Login(user.getEmail(), user.getPassword());
        if (userLogin != null) {
            this.isAuthenticated = true;
            model.addAttribute("userName", user.getNome());
            return "redirect:/teste";
        }
        model.addAttribute("error", "Usuário ou senha inválidos!");
        return "redirect:/login";
    }
    

    @GetMapping(value = "/forgot-password")
    public String forgotPassword() {
        if (this.isAuthenticated) {
            return "index";
        } else {
            return "forgot-password";
        }
    }
}
