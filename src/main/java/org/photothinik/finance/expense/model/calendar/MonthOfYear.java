package org.photothinik.finance.expense.model.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MonthOfYear {

    private Integer year;
    private Integer month;

    public MonthOfYear() {
    }

    public MonthOfYear(Date date) {
        Calendar cal = Calendar.getInstance().getInstance();
        cal.setTime(date);
        setMonth(cal.get(Calendar.MONTH) + 1);
        setYear(cal.get(Calendar.YEAR));
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthOfYear that = (MonthOfYear) o;
        return year.equals(that.year) &&
                month.equals(that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month);
    }

    @Override
    public String toString() {
        return "MonthOfYear{" +
                "year=" + year +
                ", month=" + month +
                '}';
    }
}
