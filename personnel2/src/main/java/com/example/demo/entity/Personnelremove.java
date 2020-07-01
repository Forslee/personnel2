package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * personnelremove
 * @author 
 */
@Data
public class Personnelremove implements Serializable {
    private Integer no;

    /**
     * 员工编号
     */
    private String personnelid;

    /**
     * 调动日期
     */
    private Date removedate;

    /**
     * 调后部门
     */
    private String afterremovedepartment;

    /**
     * 调后职位
     */
    private String afterremovejob;

    /**
     * 调动原因
     */
    private String removereason;

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

    public Date getRemovedate() {
        return removedate;
    }

    public void setRemovedate(Date removedate) {
        this.removedate = removedate;
    }

    public String getAfterremovedepartment() {
        return afterremovedepartment;
    }

    public void setAfterremovedepartment(String afterremovedepartment) {
        this.afterremovedepartment = afterremovedepartment;
    }

    public String getAfterremovejob() {
        return afterremovejob;
    }

    public void setAfterremovejob(String afterremovejob) {
        this.afterremovejob = afterremovejob;
    }

    public String getRemovereason() {
        return removereason;
    }

    public void setRemovereason(String removereason) {
        this.removereason = removereason;
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