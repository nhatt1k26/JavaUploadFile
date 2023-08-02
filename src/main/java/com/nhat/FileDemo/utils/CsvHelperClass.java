package com.nhat.FileDemo.utils;

import com.nhat.FileDemo.models.FileInfo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CsvHelperClass {
    public static String TYPE = "text/csv";
    public static String[] HEADERs = {"Id", "Title", "Description", "Published"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<FileInfo> csvToFileInfos(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<FileInfo> fileInfos = new ArrayList<FileInfo>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                FileInfo fileInfo = new FileInfo(
                        csvRecord.get("ID"),
                        csvRecord.get("Name"),
                        csvRecord.get("DateOfBirth"),
                        csvRecord.get("Address"),
                        csvRecord.get("Department")
                );
                fileInfos.add(fileInfo);
            }
            return fileInfos;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}