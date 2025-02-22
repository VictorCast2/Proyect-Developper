package com.App.Proyect_Developper.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Api/User")
public class UserController {

    @PreAuthorize("hasRole('User')") // 🛡️ Endpoint protegido
    @GetMapping("/Hello") // 🌍 Endpoint de saludo
    public String Admin() {
        return "👋 Hello World - Admin";
    }

}