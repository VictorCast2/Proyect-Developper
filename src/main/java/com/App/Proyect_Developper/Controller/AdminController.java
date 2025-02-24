package com.App.Proyect_Developper.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Api")
@PreAuthorize("hasRole('Admin')") // 🛡️ Endpoint protegido
public class AdminController {

    @GetMapping("/Home") // 🌍 Endpoint
    public String home() {
        return "AdminHome";
    }

}