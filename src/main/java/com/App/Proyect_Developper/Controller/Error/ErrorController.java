package com.App.Proyect_Developper.Controller.Error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Error")
public class ErrorController {

    @GetMapping("/{Code}")
    public String handleError(@PathVariable int Code) {
        return switch (Code) {
            case 300 -> "/Error/300";
            case 400 -> "/Error/400";
            case 404 -> "/Error/404";
            case 500 -> "/Error/500";
            default -> "/Error/Error";
        };
    }

}