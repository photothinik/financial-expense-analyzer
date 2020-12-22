package org.photothinik.finance.expense.model.calendar;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToMonthOfYearConvert implements Converter<String, MonthOfYear> {

    @Override
    public MonthOfYear convert(String s) {

        String[] parts = s.split("\\/");

        MonthOfYear m = new MonthOfYear();
        m.setYear(Integer.valueOf(parts[0]));
        m.setMonth(Integer.valueOf(parts[1]));

        return m;
    }
}
