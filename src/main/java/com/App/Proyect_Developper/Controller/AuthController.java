package com.App.Proyect_Developper.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Api/Auth")
public class AuthController {

    @GetMapping("/Login")
    public String Login() {
        return "Login";
    }

}