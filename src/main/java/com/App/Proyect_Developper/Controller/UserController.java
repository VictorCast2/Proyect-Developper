package com.App.Proyect_Developper.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Api/User")
public class UserController {

    @GetMapping("/Home") // 🌍 Endpoint
    public String homee() {
        return "UserHome";
    }

}