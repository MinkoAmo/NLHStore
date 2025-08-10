package com.nlhstore.controller.web;

import com.nlhstore.dto.response.CategoryResponse;
import com.nlhstore.service.impl.CategoryService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller(value = "homeControllerOfWeb")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/")
    public String homepage(Model model) {

        List<CategoryResponse> parentCategories = categoryService.findByParentCategory();
        List<CategoryResponse> productCategories = categoryService.findByHasProducts();

        model.addAttribute("parentCategories", parentCategories);
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("content", "web/pages/homepage :: index");
//        model.addAttribute("script", "web/pages/homepage :: script");
        return "layouts/web";
    }
}
