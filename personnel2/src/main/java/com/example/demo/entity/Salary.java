package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * salary
 * @author 
 */
@Data
public class Salary implements Serializable {
    /**
     * 工号
     */
    private String personnelid;

    /**
     * 员工姓名
     */
    private String personnelname;

    /**
     * 部门
     */
    private String department;

    /**
     * 奖金
     */
    private Integer bonus;

    /**
     * 基本工资
     */
    private Integer basissalary;

    /**
     * 交通补助
     */
    private Integer trafficsalary;

    /**
     * 应发工资
     */
    private Integer allsalary;

    private static final long serialVersionUID = 1L;

    public String getPersonnelid() {
        return personnelid;
    }

    public void setPersonnelid(String personnelid) {
        this.personnelid = personnelid;
    }

    public String getPersonnelname() {
        return personnelname;
    }

    public void setPersonnelname(String personnelname) {
        this.personnelname = personnelname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getBasissalary() {
        return basissalary;
    }

    public void setBasissalary(Integer basissalary) {
        this.basissalary = basissalary;
    }

    public Integer getTrafficsalary() {
        return trafficsalary;
    }

    public void setTrafficsalary(Integer trafficsalary) {
        this.trafficsalary = trafficsalary;
    }

    public Integer getAllsalary() {
        return allsalary;
    }

    public void setAllsalary(Integer allsalary) {
        this.allsalary = allsalary;
    }
}