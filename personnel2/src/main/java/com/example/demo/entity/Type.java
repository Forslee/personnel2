package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * type
 * @author 
 */
@Data
public class Type implements Serializable {
    private String typeId;

    private String typeName;

    private static final long serialVersionUID = 1L;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}