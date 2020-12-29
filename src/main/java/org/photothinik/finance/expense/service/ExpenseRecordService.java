package org.photothinik.finance.expense.service;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.model.ExpenseRecord;
import org.photothinik.finance.expense.model.calendar.MonthOfYear;
import org.photothinik.finance.expense.repository.ExpenseRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.ch.FileKey;

import java.util.*;

@Service
public class ExpenseRecordService {

    @Autowired
    private ExpenseRecordRepository expenseRecordRepository;

    public boolean isDuplicate(ExpenseRecord a, ExpenseRecord b) {

        // Transaction date
        Calendar erA = Calendar.getInstance();
        erA.setTime(a.getTransactionDate());

        Calendar erB = Calendar.getInstance();
        erB.setTime(b.getTransactionDate());

        if( erA.get(Calendar.YEAR) != erB.get(Calendar.YEAR))
            return false;
        if( erA.get(Calendar.MONTH) != erB.get(Calendar.MONTH))
            return false;
        if( erA.get(Calendar.DAY_OF_MONTH) != erB.get(Calendar.DAY_OF_MONTH))
            return false;

        // Description
        if( !a.getDescription().trim().equalsIgnoreCase(b.getDescription().trim()))
            return false;

        // Check number
        try {
            if( a.getCheckNumber().intValue() != b.getCheckNumber().intValue())
                return false;
        } catch (NullPointerException e) {
            if( a.getCheckNumber() != null || b.getCheckNumber() != null)
                return false;
        }

        // Amount
        float amtA = Math.abs(Float.valueOf(a.getAmount()));
        float amtB = Math.abs(Float.valueOf(b.getAmount()));
        if( amtA != amtB )
            return false;

        return true;
    }

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

    public List<ExpenseRecord> deleteRecordsByMonth(MonthOfYear month) {
        List<ExpenseRecord> result = new ArrayList<>();

        List<ExpenseRecord> allRecords = this.expenseRecordRepository.findAll();
        for( ExpenseRecord e : allRecords) {

            MonthOfYear m = new MonthOfYear(e.getTransactionDate());

            if( m.equals(month)) {
                result.add(e);
                delete(e);
            }
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
