package org.photothinik.finance.expense.controller;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.model.ExpenseRecord;
import org.photothinik.finance.expense.model.reportselection.ReportSelection;
import org.photothinik.finance.expense.service.CategoryService;
import org.photothinik.finance.expense.service.ExpenseRecordService;
import org.photothinik.finance.expense.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ExpenseRecordService expenseRecordService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("availableMonths", this.expenseRecordService.getMonthsWithData());
        model.addAttribute("selectedReport", new ReportSelection());
        return "home";
    }

    @PostMapping("/")
    public String selectReportTimePeriod(@ModelAttribute ReportSelection reportSelection, Model model) {

        model.addAttribute("availableMonths", this.expenseRecordService.getMonthsWithData());
        model.addAttribute("selectedReport", reportSelection);

        // Include report
        if( reportSelection.valid() ) {
            model.addAttribute("report", this.reportService.getReport(reportSelection));
        }

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

        model.addAttribute("categories", this.categoryService.getAllCategories());
        model.addAttribute("newExpenseRecord", new ExpenseRecord());

        return "data";
    }

    @PostMapping("/data")
    public String addExpenseRecord(@ModelAttribute ExpenseRecord expenseRecord, Model model) {

        model.addAttribute("categories", this.categoryService.getAllCategories());


        try {
            this.expenseRecordService.save(expenseRecord);

            model.addAttribute("newExpenseRecord", new ExpenseRecord());

        } catch (Exception e) {
            model.addAttribute("errorMsg", "Unable to save ("+e.getClass().getSimpleName()+"): " + e.getMessage());
            model.addAttribute("newExpenseRecord", expenseRecord);
            e.printStackTrace();
        }


        return "data";
    }

}
