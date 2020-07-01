package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * operate
 * @author 
 */
@Data
public class Operate implements Serializable {
    /**
     * 序号
     */
    private Integer no;

    /**
     * 操作员
     */
    private String op;

    /**
     * 添加日期
     */
    private Date adddate;

    /**
     * 事件内容
     */
    private String event;

    private static final long serialVersionUID = 1L;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}