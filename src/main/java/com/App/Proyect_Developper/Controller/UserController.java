package com.App.Proyect_Developper.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Api/User")
@PreAuthorize("hasRole('User')") // 🛡️ Endpoint protegido
public class UserController {

    @GetMapping("/Home") // 🌍 Endpoint
    public String home() {
        return "UserHome";
    }

}