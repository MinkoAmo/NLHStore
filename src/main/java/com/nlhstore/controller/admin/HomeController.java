package com.nlhstore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "homeControllerOfAdmin")
@RequestMapping("/admin")
public class HomeController {

    @GetMapping("")
        public String homepage(Model model) {
        model.addAttribute("content", "admin/pages/homepage :: index");
        return "layouts/admin";
    }
}
