package org.photothinik.finance.expense.model;

import java.util.ArrayList;
import java.util.List;

public class ImportPreview {

    private List<ExpenseRecord> records = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();

    public ImportPreview() {
    }

    public List<ExpenseRecord> getRecords() {
        return records;
    }

    public void setRecords(List<ExpenseRecord> records) {
        this.records = records;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
