package org.photothinik.finance.expense.controller;

import org.photothinik.finance.expense.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", "Financial Expense Analyzer");
        return "home";
    }

    @GetMapping("/categories")
    public String categories(Model model) {

        model.addAttribute("categories", this.categoryService.getAllCategories());

        return "categories";
    }

}
