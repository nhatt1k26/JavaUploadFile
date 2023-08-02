package com.nhat.FileDemo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    @Id
    @Column(length = 20)
    private String id;
    private String name;
    private String dateOfBirth;
    private String address;
    private String department;
}
