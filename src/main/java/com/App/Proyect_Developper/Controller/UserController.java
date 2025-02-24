package com.App.Proyect_Developper.Controller;

import com.App.Proyect_Developper.Model.UserModel;
import com.App.Proyect_Developper.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/Api/User")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/Home") // 🌍 Endpoint
    public String homee() {
        return "UserHome";
    }

    @GetMapping("/add")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "RegistraUser";
    }

    @PostMapping("/add")
    public String registerUser(@ModelAttribute UserModel user) {
        user.setRoles(Collections.singleton("ROLE_User"));
        userServices.registrarUsuario(user.getUsername(), user.getPassword(), user.getRoles().iterator().next());
        return "redirect:/Api/Auth/Login";
    }

}