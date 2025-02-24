package com.App.Proyect_Developper.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Api")
public class UserController {

    @GetMapping("/HomeUser") // 🌍 Endpoint
    public String homee() {
        return "UserHome";
    }

}