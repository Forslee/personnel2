package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * personnelencourageorpunish
 * @author 
 */
@Data
public class Personnelencourageorpunish implements Serializable {
    private Integer no;

    /**
     * 员工编号
     */
    private String personnelid;

    /**
     * 奖罚日期
     */
    private Date encorpunishdate;

    /**
     * 奖罚原因
     */
    private String encorpunishreason;

    /**
     * 奖罚分
     */
    private Integer encorpunishpoint;

    private String type;

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

    public Date getEncorpunishdate() {
        return encorpunishdate;
    }

    public void setEncorpunishdate(Date encorpunishdate) {
        this.encorpunishdate = encorpunishdate;
    }

    public String getEncorpunishreason() {
        return encorpunishreason;
    }

    public void setEncorpunishreason(String encorpunishreason) {
        this.encorpunishreason = encorpunishreason;
    }

    public Integer getEncorpunishpoint() {
        return encorpunishpoint;
    }

    public void setEncorpunishpoint(Integer encorpunishpoint) {
        this.encorpunishpoint = encorpunishpoint;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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