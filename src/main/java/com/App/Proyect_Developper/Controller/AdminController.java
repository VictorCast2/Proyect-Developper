package com.App.Proyect_Developper.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Api/Admin")
public class AdminController {

    @GetMapping("/Home") // 🌍 Endpoint
    public String homea() {
        return "AdminHome";
    }

}