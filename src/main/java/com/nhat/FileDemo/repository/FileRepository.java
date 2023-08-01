package com.nhat.FileDemo.repository;

import com.nhat.FileDemo.models.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileInfo, Long> {
    List<FileInfo> findAllByDepartment(String department);
}
