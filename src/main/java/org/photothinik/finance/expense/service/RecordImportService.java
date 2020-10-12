package org.photothinik.finance.expense.service;

import com.opencsv.CSVReader;
import org.photothinik.finance.expense.model.ExpenseRecord;
import org.photothinik.finance.expense.model.ImportPreview;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RecordImportService {

    public ImportPreview getImportPreview(File file) {
        ImportPreview result = new ImportPreview();


        // Read the CSV
        try {
            List<List<String>> records = new ArrayList<>();
            try (CSVReader csvReader = new CSVReader(new FileReader(file));) {
                String[] values = null;
                while ((values = csvReader.readNext()) != null) {
                    records.add(Arrays.asList(values));
                }
            }

            // Assemble results
            SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

            for( List<String> outer : records) {

                try {

                    ExpenseRecord er = new ExpenseRecord();

                    // Date
                    er.setTransactionDate(dateFormat.parse(outer.get(0)));

                    // Description
                    er.setDescription(outer.get(1));

                    // Check number
                    try {
                        er.setCheckNumber(Integer.valueOf(outer.get(2)));
                    } catch (NumberFormatException nfe) {
                        er.setCheckNumber(null);
                    }

                    // Amount
                    er.setAmount(outer.get(3));

                    // Commit
                    result.getRecords().add(er);

                } catch (Exception e) {
                    result.getWarnings().add("An error occurred (" + e.getClass().getSimpleName() + ") while parsing record '" + outer + "': " + e.getMessage());
                    e.printStackTrace();
                }

            }



        } catch (IOException e) {
            throw new RuntimeException("Unable to read file '" + file.getAbsolutePath() + "': " + e.getMessage());
        }

        return result;
    }

}
