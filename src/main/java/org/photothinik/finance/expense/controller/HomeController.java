package org.photothinik.finance.expense.controller;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.model.DataCleanup;
import org.photothinik.finance.expense.model.ExpenseRecord;
import org.photothinik.finance.expense.model.ImportPreview;
import org.photothinik.finance.expense.model.calendar.MonthOfYear;
import org.photothinik.finance.expense.model.reportselection.ReportSelection;
import org.photothinik.finance.expense.service.CategoryService;
import org.photothinik.finance.expense.service.ExpenseRecordService;
import org.photothinik.finance.expense.service.RecordImportService;
import org.photothinik.finance.expense.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ExpenseRecordService expenseRecordService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private RecordImportService recordImportService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("availableMonths", this.expenseRecordService.getMonthsWithData());
        model.addAttribute("selectedReport", new ReportSelection());
        model.addAttribute("categories", this.categoryService.getAllCategories());
        return "home";
    }

    @PostMapping("/")
    public String selectReportTimePeriod(@ModelAttribute ReportSelection reportSelection, Model model) {

        model.addAttribute("availableMonths", this.expenseRecordService.getMonthsWithData());
        model.addAttribute("selectedReport", reportSelection);
        model.addAttribute("categories", this.categoryService.getAllCategories());

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

        model.addAttribute("availableMonths", this.expenseRecordService.getMonthsWithData());
        model.addAttribute("categories", this.categoryService.getAllCategories());
        model.addAttribute("newExpenseRecord", new ExpenseRecord());
        model.addAttribute("dataCleanup", new DataCleanup());

        return "data";
    }

    @PostMapping("/dataDeleteMonth")
    public String deleteMonth(@ModelAttribute DataCleanup dataCleanup, Model model) {

        model.addAttribute("categories", this.categoryService.getAllCategories());
        model.addAttribute("newExpenseRecord", new ExpenseRecord());
        model.addAttribute("dataCleanup", new DataCleanup());

        // Delete
        this.expenseRecordService.deleteRecordsByMonth(dataCleanup.getMonthToDelete());

        // Populate available months
        model.addAttribute("availableMonths", this.expenseRecordService.getMonthsWithData());

        return "data";
    }

//    @PostMapping("/dataapprove")
//    public String approveUpload(@ModelAttribute ImportPreview preview, Model model) {
//
//        model.addAttribute("categories", this.categoryService.getAllCategories());
//        model.addAttribute("newExpenseRecord", new ExpenseRecord());
//
//        try {
//            for (ExpenseRecord r : preview.getRecords()) {
//                this.expenseRecordService.save(r);
//            }
//        } catch(Exception e) {
//            model.addAttribute("uploadError", "A problem occurred while load data ("+e.getClass().getSimpleName()+"): " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        return "data";
//    }

    @PostMapping("/dataupload")
    public String uploadDataFile(@RequestParam("recordsFile")MultipartFile file, Model model) {

        model.addAttribute("categories", this.categoryService.getAllCategories());
        model.addAttribute("newExpenseRecord", new ExpenseRecord());
        model.addAttribute("dataCleanup", new DataCleanup());

        if( file.isEmpty()) {
            model.addAttribute("uploadError", "The uploaded file was empty");
        }
        else
        {
            try {

//                File localFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
//                if( localFile.exists())
//                    localFile.delete();
//                localFile.createNewFile();
//                file.transferTo(localFile);


                Path filepath = Paths.get(System.getProperty("user.dir"), file.getOriginalFilename());
                file.transferTo(filepath);


                ImportPreview preview = this.recordImportService.getImportPreview(filepath.toFile());

                model.addAttribute("preview", preview);

                for (ExpenseRecord r : preview.getRecords()) {
                    this.expenseRecordService.save(r);
                }
            } catch(Exception e) {
                model.addAttribute("uploadError", "A problem occurred while loading data ("+e.getClass().getSimpleName()+"): " + e.getMessage());
                e.printStackTrace();
            }

        }

        return "data";

    }

    @PostMapping("/data")
    public String addExpenseRecord(@ModelAttribute ExpenseRecord expenseRecord, Model model) {

        model.addAttribute("categories", this.categoryService.getAllCategories());
        model.addAttribute("dataCleanup", new DataCleanup());


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
