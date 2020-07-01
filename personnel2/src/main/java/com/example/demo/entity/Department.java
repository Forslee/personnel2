package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * department
 * @author 
 */
@Data
public class Department implements Serializable {
    private String departmentName;

    private String departmentId;

    private static final long serialVersionUID = 1L;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}