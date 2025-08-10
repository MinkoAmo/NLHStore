package com.nlhstore.controller.admin;

import com.nlhstore.dto.response.CategoryResponse;
import com.nlhstore.service.impl.CategoryService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/category")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "0") int page,
                           Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryResponse> pageDTO = categoryService.findAll(pageable);
        int from = (page * size) + 1;
        model.addAttribute("content", "/admin/pages/category/index :: categoryList");
        model.addAttribute("script", "/admin/pages/category/index :: script");
        model.addAttribute("models", pageDTO.getContent());
        model.addAttribute("pageDTO", pageDTO);
        model.addAttribute("from", from);
        model.addAttribute("to", Math.min(from + size - 1, (int) pageDTO.getTotalElements()));
        return "layouts/admin";
    }

    @GetMapping({"/edit", "/edit/{id}"})
    public String insertForm(@PathVariable(required = false) Long id, Model model) {
        CategoryResponse response = new CategoryResponse();
        if (id != null) {
            response = categoryService.findById(id);
        }
        model.addAttribute("content", "/admin/pages/category/editCategory :: editCategory");
        model.addAttribute("script", "/admin/pages/category/editCategory :: script");
        model.addAttribute("model", response);
        return "layouts/admin";
    }
}
