package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * op
 * @author 
 */
@Data
public class Op implements Serializable {
    /**
     * 账号
     */
    private String opid;

    private String oppassword;

    /**
     * 操作员
     */
    private String opname;

    /**
     * 联系电话
     */
    private String opphone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 权限
     */
    private String permission;

    private static final long serialVersionUID = 1L;

    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid;
    }

    public String getOppassword() {
        return oppassword;
    }

    public void setOppassword(String oppassword) {
        this.oppassword = oppassword;
    }

    public String getOpname() {
        return opname;
    }

    public void setOpname(String opname) {
        this.opname = opname;
    }

    public String getOpphone() {
        return opphone;
    }

    public void setOpphone(String opphone) {
        this.opphone = opphone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}