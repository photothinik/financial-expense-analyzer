package org.photothinik.finance.expense.controller;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.model.CategoryPattern;
import org.photothinik.finance.expense.model.ExpenseRecord;
import org.photothinik.finance.expense.service.CategoryPatternService;
import org.photothinik.finance.expense.service.CategoryService;
import org.photothinik.finance.expense.service.ExpenseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryPatternService categoryPatternService;

    @Autowired
    private ExpenseRecordService expenseRecordService;

    @PostMapping("/api/categoryupdate")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {

        this.categoryService.save(category);

        return ResponseEntity.ok(category);
    }

    @PostMapping("/api/expenselabel")
    public ResponseEntity<?> expenseRecordLabelUpdate(@RequestBody ExpenseRecord expenseRecord) {

        ExpenseRecord er = this.expenseRecordService.getById(expenseRecord.getId());

        er.setLabel((expenseRecord.getLabel() == null || "".equalsIgnoreCase(expenseRecord.getLabel().trim()) ? null : expenseRecord.getLabel()));

        this.expenseRecordService.save(er);

        return ResponseEntity.ok(er);
    }

    @GetMapping("/api/categoryoverride")
    public ResponseEntity<?> expenseCategoryOverride(
            @RequestParam("expenseid") Long expenseId,
            @RequestParam("categoryid") Long categoryId
    ) {

        ExpenseRecord er = this.expenseRecordService.getById(expenseId);

        er.setCategoryIdOverride(categoryId);

        this.expenseRecordService.save(er);

        return ResponseEntity.ok(er);
    }

    @DeleteMapping("/api/categoryupdate")
    public ResponseEntity<?> deleteCategory(@RequestBody Category category) {

        this.categoryService.delete(category);

        return ResponseEntity.ok(category);
    }

    @PutMapping("/api/categorypatterncreate")
    public ResponseEntity<?> createPattern(@RequestBody CategoryPattern categoryPattern) {

        this.categoryPatternService.save(categoryPattern);

        return ResponseEntity.ok(categoryPattern);
    }

    @DeleteMapping("/api/categorypatterndelete")
    public ResponseEntity<?> deletePattern(@RequestBody CategoryPattern categoryPattern) {

        this.categoryPatternService.delete(categoryPattern);

        return ResponseEntity.ok(categoryPattern);
    }
}
