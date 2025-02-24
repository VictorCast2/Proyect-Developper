package com.App.Proyect_Developper.Controller;

import com.App.Proyect_Developper.Model.UserModel;
import com.App.Proyect_Developper.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/Api/Admin")
public class AdminController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/Home") // 🌍 Endpoint
    public String homea() {
        return "AdminHome";
    }

    @GetMapping("/add")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "RegistraUser";
    }

    @PostMapping("/add")
    public String registerUser(@ModelAttribute UserModel user) {
        user.setRoles(Collections.singleton("ROLE_Admin"));
        userServices.registrarUsuario(user.getUsername(), user.getPassword(), user.getRoles().iterator().next());
        return "redirect:/Api/Auth/Login";
    }

}