package org.photothinik.finance.expense.service;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.model.CategoryPattern;
import org.photothinik.finance.expense.model.ExpenseRecord;
import org.photothinik.finance.expense.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryPatternService categoryPatternService;

    @Autowired
    private ExpenseRecordService expenseRecordService;

    public List<Category> getAllCategories() {return this.categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));}

    public Category getById(long id) {

        Optional<Category> result = this.categoryRepository.findById(id);

        if( result.isPresent())
            return result.get();
        else
            throw new IllegalArgumentException("No category found with ID " + id);
    }

    public Category save(Category c) {return this.categoryRepository.save(c);}

    public Category delete(Category c) {

        // Set to null any expense records which are affiliated with this category
        List<ExpenseRecord> affiliatedRecords = this.expenseRecordService.getExpenseRecordsByCategory(c);
        for(ExpenseRecord e : affiliatedRecords) {
            e.setCategoryIdOverride(null);
            this.expenseRecordService.save(e);
        }

        // Delete all category patterns that belong to this category
        List<CategoryPattern> patterns = this.categoryPatternService.getAllPatternsByCategory(c);
        for(CategoryPattern p : patterns) {
            c.getPatterns().remove(p);
            this.categoryPatternService.delete(p);
        }

        // Save category without patterns
//        this.categoryRepository.save(c);

        // Get the most recent copy of the category object
        Category mostRecent = this.categoryRepository.findById(c.getId()).get();

        // Delete category
        this.categoryRepository.delete(mostRecent);

        return mostRecent;
    }
}
