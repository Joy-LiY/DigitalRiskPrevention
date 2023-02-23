package com.example.digitalriskprevention.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/22
 * @Description: 需求基本信息
 * @Version: 1.0
 */
@Data
@TableName(value = "requirement")
public class Requirement implements Serializable {

    private static final long serialVersionUID = -5418780975844892605L;
    @TableId
    private String id;
    /**
     * 序号
     */
    private Integer order;
    /**
     * 厂家
     */

    private String factory;
    /**
     * 归属系统
     */
    private String homeSystem;
    /**
     * 需求编号
     */
    private String number;
    /**
     * 需求名称
     */
    private String title;

    public Requirement(String id) {
        this.id = id;
    }
}
