package com.nhat.FileDemo.utils;

import com.nhat.FileDemo.models.FileInfo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvFileGenerator {
    public void writeToCsv(List<FileInfo> fileInfoList, Writer writer){
        try{
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord("ID","Name","DateOfBirth","Address","Department");
            for(FileInfo fileInfo : fileInfoList){
                printer.printRecord(
                        fileInfo.getId(),
                        fileInfo.getName(),
                        fileInfo.getDateOfBirth(),
                        fileInfo.getAddress(),
                        fileInfo.getDepartment());
            }}
            catch (IOException e){
                e.printStackTrace();
        }
    }
}
