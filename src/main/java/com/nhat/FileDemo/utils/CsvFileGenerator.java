package com.nhat.FileDemo.utils;

import com.nhat.FileDemo.models.FileInfo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvFileGenerator {

    public static ByteArrayInputStream toByteCSV(List<FileInfo> fileInfoList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (FileInfo fileInfo : fileInfoList) {
                List<String> data = Arrays.asList(
                        fileInfo.getId(),
                        fileInfo.getName(),
                        fileInfo.getDateOfBirth(),
                        fileInfo.getAddress(),
                        fileInfo.getDepartment());
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
