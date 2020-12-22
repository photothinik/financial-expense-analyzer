package org.photothinik.finance.expense.model.reportselection;

import org.photothinik.finance.expense.model.calendar.MonthOfYear;

public class ReportSelection {

    private MonthOfYear monthOfYear;

    public ReportSelection() {
    }

    public boolean valid() {
        if( this.monthOfYear != null && this.monthOfYear.getMonth() != null && this.monthOfYear.getYear() != null)
            return true;
        else
            return false;
    }

    public MonthOfYear getMonthOfYear() {
        return monthOfYear;
    }

    public void setMonthOfYear(MonthOfYear monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    @Override
    public String toString() {
        return "ReportSelection{" +
                "monthOfYear=" + monthOfYear +
                '}';
    }
}
