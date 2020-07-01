package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Description：
 * DATE:2020/4/23 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Result<T> {
    private boolean state = true;
    private String msg;
    private String userid;//操作员或系统管理员id
    private T data;

}
