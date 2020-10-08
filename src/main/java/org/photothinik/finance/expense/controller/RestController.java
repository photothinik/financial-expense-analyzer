package org.photothinik.finance.expense.controller;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.model.CategoryPattern;
import org.photothinik.finance.expense.service.CategoryPatternService;
import org.photothinik.finance.expense.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryPatternService categoryPatternService;

    @PostMapping("/api/categoryupdate")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {

        this.categoryService.save(category);

        return ResponseEntity.ok(category);
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
