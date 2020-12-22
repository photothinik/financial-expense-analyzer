package org.photothinik.finance.expense.model;

import org.photothinik.finance.expense.model.calendar.MonthOfYear;

public class DataCleanup {

    private MonthOfYear monthToDelete;

    public DataCleanup() {
    }

    public MonthOfYear getMonthToDelete() {
        return monthToDelete;
    }

    public void setMonthToDelete(MonthOfYear monthToDelete) {
        this.monthToDelete = monthToDelete;
    }
}
