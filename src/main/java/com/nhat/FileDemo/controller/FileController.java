package com.nhat.FileDemo.controller;

import com.nhat.FileDemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/csv")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> getFileInfo(@RequestPart("file") MultipartFile userFile) {
        fileService.uploadFile(userFile);
        return ResponseEntity.ok("Success");
    }

    @PostMapping(value = "/uploadMultipleFile")
    public ResponseEntity<String> getFileInfo(@RequestPart("file") MultipartFile[] userFiles) {
        for (MultipartFile userFile : userFiles) {
            fileService.uploadFile(userFile);
        }
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/getAll")
    public ResponseEntity<Resource> getAllFileInfo() {
        String filename = "allFile.csv";
        InputStreamResource file = new InputStreamResource(fileService.findAllStream());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping(value = "/getByDepartment/{department}")
    public ResponseEntity<Resource> exportToCSV(@PathVariable String department) throws IOException {
        String filename = "Department_" + department + ".csv";

        InputStreamResource file = new InputStreamResource(fileService.findAllByDepartmentStream(department));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
