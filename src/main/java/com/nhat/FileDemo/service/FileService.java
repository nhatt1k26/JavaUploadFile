package com.nhat.FileDemo.service;

import com.nhat.FileDemo.models.FileInfo;
import com.nhat.FileDemo.repository.FileRepository;
import com.nhat.FileDemo.utils.CsvFileGenerator;
import com.nhat.FileDemo.utils.CsvHelperClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    @Autowired
    FileRepository fileRepository;

    public void uploadFile(MultipartFile multipartFile) {
        try {
            List<FileInfo> fileInfos = CsvHelperClass.csvToFileInfos(multipartFile.getInputStream());
            fileRepository.saveAll(fileInfos);
        } catch (IOException e) {
            throw new RuntimeException("fail to story csv data:" + e.getMessage());
        }
    }

    public List<FileInfo> findAll() {
        return fileRepository.findAll();
    }

    public ByteArrayInputStream findAllStream() {
        return CsvFileGenerator.toByteCSV(findAll());
    }

    public List<FileInfo> findAllByDepartment(String department) {
        return fileRepository.findAllByDepartment(department);
    }

    public ByteArrayInputStream findAllByDepartmentStream(String department){
        return CsvFileGenerator.toByteCSV(findAllByDepartment(department));
    }
}
