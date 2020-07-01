package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * personnelinfo
 * @author 
 */
public class Personnelinfo implements Serializable {
    /**
     * 工号
     */
    private String personnelid;

    /**
     * 员工姓名
     */
    private String personnelname;

    /**
     * 员工性别
     */
    private String personnelsex;

    /**
     * 身份证号
     */
    private String id;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 电子邮箱
     */
    private String eMail;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 部门
     */
    private String department;

    /**
     * 职位
     */
    private String job;

    private Integer basissalary;

    /**
     * 入职日期
     */
    private Date beginworkdate;

    /**
     * 在职状态
     */
    private String workstate;

    /**
     * 离职日期
     */
    private Date notworkdate;

    /**
     * 工龄
     */
    private Integer workage;

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

    public String getPersonnelsex() {
        return personnelsex;
    }

    public void setPersonnelsex(String personnelsex) {
        this.personnelsex = personnelsex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getBasissalary() {
        return basissalary;
    }

    public void setBasissalary(Integer basissalary) {
        this.basissalary = basissalary;
    }

    public Date getBeginworkdate() {
        return beginworkdate;
    }

    public void setBeginworkdate(Date beginworkdate) {
        this.beginworkdate = beginworkdate;
    }

    public String getWorkstate() {
        return workstate;
    }

    public void setWorkstate(String workstate) {
        this.workstate = workstate;
    }

    public Date getNotworkdate() {
        return notworkdate;
    }

    public void setNotworkdate(Date notworkdate) {
        this.notworkdate = notworkdate;
    }

    public Integer getWorkage() {
        return workage;
    }

    public void setWorkage(Integer workage) {
        this.workage = workage;
    }
}