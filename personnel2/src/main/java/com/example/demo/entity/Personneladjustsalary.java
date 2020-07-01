package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * personneladjustsalary
 * @author 
 */
@Data
public class Personneladjustsalary implements Serializable {
    private Integer no;

    /**
     * 员工编号
     */
    private String personnelid;

    /**
     * 调薪日期
     */
    private Date adjustsalarydate;

    /**
     * 调前薪资
     */
    private Integer beforeadjustsalary;

    /**
     * 调后薪资
     */
    private Integer afteradjustsalary;

    /**
     * 调薪原因
     */
    private String adjustsalaryreason;

    /**
     * 备注
     */
    private String remark;

    private String op;

    private static final long serialVersionUID = 1L;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getPersonnelid() {
        return personnelid;
    }

    public void setPersonnelid(String personnelid) {
        this.personnelid = personnelid;
    }

    public Date getAdjustsalarydate() {
        return adjustsalarydate;
    }

    public void setAdjustsalarydate(Date adjustsalarydate) {
        this.adjustsalarydate = adjustsalarydate;
    }

    public Integer getBeforeadjustsalary(Integer basissalary) {
        return beforeadjustsalary;
    }

    public void setBeforeadjustsalary(Integer beforeadjustsalary) {
        this.beforeadjustsalary = beforeadjustsalary;
    }

    public Integer getAfteradjustsalary() {
        return afteradjustsalary;
    }

    public void setAfteradjustsalary(Integer afteradjustsalary) {
        this.afteradjustsalary = afteradjustsalary;
    }

    public String getAdjustsalaryreason() {
        return adjustsalaryreason;
    }

    public void setAdjustsalaryreason(String adjustsalaryreason) {
        this.adjustsalaryreason = adjustsalaryreason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}