package org.photothinik.finance.expense.controller;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        model.addAttribute("newCategory", new Category());

        return "categories";
    }

    @PostMapping("/categories")
    public String createCategory(@ModelAttribute Category newCategory, Model model) {

        this.categoryService.save(newCategory);

        model.addAttribute("categories", this.categoryService.getAllCategories());
        model.addAttribute("newCategory", new Category());

        return "categories";
    }

    @GetMapping("/data")
    public String dataLoad(Model model) {



        return "data";
    }

}
