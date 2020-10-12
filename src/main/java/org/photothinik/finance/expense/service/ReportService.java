package org.photothinik.finance.expense.service;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.model.CategoryPattern;
import org.photothinik.finance.expense.model.ExpenseRecord;
import org.photothinik.finance.expense.model.Report;
import org.photothinik.finance.expense.model.reportselection.ReportSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    @Autowired
    private ExpenseRecordService expenseRecordService;

    @Autowired
    private CategoryPatternService categoryPatternService;

    @Autowired
    private CategoryService categoryService;

    private boolean isDateInRSRange(Date date, ReportSelection rs) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if( calendar.get(Calendar.YEAR) == rs.getMonthOfYear().getYear().intValue() && (calendar.get(Calendar.MONTH)+ 1) == rs.getMonthOfYear().getMonth())
            return true;

        return false;
    }

    public Report getReport(ReportSelection rs) {

        // New report
        Report result = new Report();

        // Get components
        List<ExpenseRecord> allRecords = this.expenseRecordService.getAllExpenseRecords();
        List<Category> categories = this.categoryService.getAllCategories();

        Map<Category, Float> subtotalsMap = new HashMap<>();

        float unmatchedSubtotal = 0f;

        // Match records up by category
        for(ExpenseRecord record : allRecords) {

            if( !isDateInRSRange(record.getTransactionDate(), rs))
                continue;

            // Check for override
            if( record.getCategoryIdOverride() != null)
            {
                Category overrideCategory = this.categoryService.getById(record.getCategoryIdOverride().longValue());
                if( result.getExpenseRecordsByCategory().get(overrideCategory) == null)
                    result.getExpenseRecordsByCategory().put(overrideCategory, new ArrayList<>());
                result.getExpenseRecordsByCategory().get(overrideCategory).add(record);
                continue;
            }

            boolean matchFound = false;


            // See if this record matches any of the category patterns
            for(Category category : categories) {

                if( result.getExpenseRecordsByCategory().get(category) == null)
                    result.getExpenseRecordsByCategory().put(category, new ArrayList<>());


                for(CategoryPattern pattern : category.getPatterns()) {

                    try {

                        if (record.getDescription().matches(pattern.getPattern())) {
                            result.getExpenseRecordsByCategory().get(category).add(record);
                            matchFound = true;

                            // Include in subtotal
                            if( subtotalsMap.get(category) == null)
                                subtotalsMap.put(category, new Float(Float.valueOf(record.getAmount())));
                            else
                                subtotalsMap.put(category, subtotalsMap.get(category).floatValue() + Float.valueOf(record.getAmount()));
                        }
                    } catch(Exception e) {
                        result.getWarnings().add("Exception while evaluating pattern '" + pattern.getPattern() + "': " + e.getMessage());
                        e.printStackTrace();
                    }

                }

            }

            // Add to unmatched, if nothing else matched
            if(!matchFound) {
                result.getUncategorized().add(record);
                unmatchedSubtotal+= Float.valueOf(record.getAmount());
            }

        }

        // Convert subtotal map
        for(Category c : subtotalsMap.keySet()) {
            result.getCategoriesBySubtotal().put(c, String.valueOf("$" + subtotalsMap.get(c)));
        }

        result.setUnmatchedSubtotal(new Float(unmatchedSubtotal));


        return result;
    }

}
