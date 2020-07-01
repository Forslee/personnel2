package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * sys
 * @author 
 */
@Data
public class Sys implements Serializable {
    private String sysno;

    private String sysName;

    private String sysPassword;

    private static final long serialVersionUID = 1L;

    public String getSysno() {
        return sysno;
    }

    public void setSysno(String sysno) {
        this.sysno = sysno;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysPassword() {
        return sysPassword;
    }

    public void setSysPassword(String sysPassword) {
        this.sysPassword = sysPassword;
    }
}