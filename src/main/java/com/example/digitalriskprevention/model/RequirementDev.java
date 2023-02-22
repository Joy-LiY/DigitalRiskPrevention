package com.example.digitalriskprevention.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/22
 * @Description: 需求开发情况
 * @Version: 1.0
 */
@Data
@TableName(value = "requirement_dev")
public class RequirementDev implements Serializable {
    private static final long serialVersionUID = 4260925894504074446L;
    /**
     * 需求开发主键ID
     */
    @TableId
    private String devId;

    /**
     * 关联需求基本信息的主键
     */
    private String requirementId;

    /**
     * 计划上线时间
     */
    private Date planTime;
    /**
     * 实际上线时间
     */
    private Date realTime;

    /**
     * 开发时长
     */
    private Double devDuration;

    /**
     * 是否超时
     */
    private String isOvertime;

    /**
     * 开发状态
     */
    private String devStatus;

    /**
     * 原因（超时及未上线）
     */
    private String reason;

}
