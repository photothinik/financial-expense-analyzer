package org.photothinik.finance.expense.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {

    private Map<Category, List<ExpenseRecord>> expenseRecordsByCategory = new HashMap<>();

    private Map<Category, String> categoriesBySubtotal = new HashMap<>();

    private Float unmatchedSubtotal;

    private List<ExpenseRecord> uncategorized = new ArrayList<>();

    private List<String> warnings = new ArrayList<>();


    public Report() {
    }

    public Map<Category, List<ExpenseRecord>> getExpenseRecordsByCategory() {
        return expenseRecordsByCategory;
    }

    public List<ExpenseRecord> getUncategorized() {
        return uncategorized;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public Map<Category, String> getCategoriesBySubtotal() {
        return categoriesBySubtotal;
    }

    public Float getUnmatchedSubtotal() {
        return unmatchedSubtotal;
    }

    public void setUnmatchedSubtotal(Float unmatchedSubtotal) {
        this.unmatchedSubtotal = unmatchedSubtotal;
    }
}
