package org.photothinik.finance.expense.service;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.model.ExpenseRecord;
import org.photothinik.finance.expense.model.reportselection.MonthOfYear;
import org.photothinik.finance.expense.repository.ExpenseRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.ZoneId;
import java.util.*;

@Service
public class ExpenseRecordService {

    @Autowired
    private ExpenseRecordRepository expenseRecordRepository;

    public List<MonthOfYear> getMonthsWithData() {
        List<MonthOfYear> result = new ArrayList<>();

        List<ExpenseRecord> allRecords = this.expenseRecordRepository.findAll();
        for( ExpenseRecord e : allRecords) {

            MonthOfYear m = new MonthOfYear(e.getTransactionDate());

            if( !result.contains(m))
                result.add(m);
        }

        return result;
    }

    public ExpenseRecord save(ExpenseRecord e) {

        // Make sure the amount string is usable
        e.setAmount(e.getAmount().replaceAll("[^\\d.]+", ""));

        // Ensure valid float
        Float.parseFloat(e.getAmount());

        // Set the override to null, if -1
        if( e.getCategoryIdOverride() != null && e.getCategoryIdOverride().longValue() == -1)
            e.setCategoryIdOverride(null);

        // Set upper case
        e.setDescription(e.getDescription().toUpperCase());

        // Save
        this.expenseRecordRepository.save(e);
        return e;
    }

    public ExpenseRecord delete(ExpenseRecord e) {
        this.expenseRecordRepository.delete(e);
        return e;
    }

    public List<ExpenseRecord> getAllExpenseRecords() {return this.expenseRecordRepository.findAll();}

    public List<ExpenseRecord> getExpenseRecordsByCategory(Category category) {

        List<ExpenseRecord> result = new ArrayList<>();
        List<ExpenseRecord> allRecords = this.expenseRecordRepository.findAll();

        for(ExpenseRecord e : allRecords) {
            if( e.getCategoryIdOverride() != null && e.getCategoryIdOverride().longValue() == category.getId().longValue())
                result.add(e);
        }

        return result;
    }

    public ExpenseRecord getById(long id) {

        Optional<ExpenseRecord> result = this.expenseRecordRepository.findById(id);

        if( result.isPresent())
            return result.get();
        else
            throw new IllegalArgumentException("No expense record found with ID " + id);
    }

}
