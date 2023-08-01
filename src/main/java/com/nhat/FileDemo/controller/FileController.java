package com.nhat.FileDemo.controller;

import com.nhat.FileDemo.models.FileInfo;
import com.nhat.FileDemo.service.FileService;
import com.nhat.FileDemo.utils.CsvFileGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class FileController {
    @Autowired
    FileService fileService;

    @Autowired
    CsvFileGenerator csvFileGenerator;
    @PostMapping(value="/upload")
    public ResponseEntity<String> getFileInfo(@RequestPart("file") MultipartFile userFile){
        fileService.uploadFile(userFile);
        return ResponseEntity.ok("Success");
    }

    @PostMapping(value="/uploadMultipleFile")
    public ResponseEntity<String> getFileInfo(@RequestPart("file") MultipartFile[] userFiles){
        for (MultipartFile userFile:userFiles)
        {
            fileService.uploadFile(userFile);
        }
        return ResponseEntity.ok("Success");
    }

    @GetMapping(value = "/getByDepartment/{department}")
    public void exportToCSV(@PathVariable String department, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"Department.csv\"");
        List<FileInfo> fileInfoList = fileService.findAllByDepartment(department);
//        System.out.println(fileInfoList);
        csvFileGenerator.writeToCsv(fileInfoList,response.getWriter());
    }
}
