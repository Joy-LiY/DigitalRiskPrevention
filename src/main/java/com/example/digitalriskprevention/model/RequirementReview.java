package com.example.digitalriskprevention.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/22
 * @Description: 需求评审情况
 * @Version: 1.0
 */
@Data
@TableName(value = "requirement_review")
public class RequirementReview implements Serializable {
    private static final long serialVersionUID = -4272534224145072844L;
    /**
     * 评审表主键
     */
    @TableId
    private String reviewId;
    /**
     * 关联需求基本信息的主键
     */
    private String requirementId;
    /**
     * 需求描述
     */
    private String description;
    /**
     * 需求类型
     */
    private String type;
    /**
     * 需求负责人
     */
    private String leader;
    /**
     * 需求提出人
     */
    private String presenter;

    /**
     * 需求提出部门
     */
     private String department;
    /**
     * 预计工作量（人天）
     */
     private Double planWorkload;
    /**
     * 初审工作量（人天）
     */
    private Double firstReviewWorkload;

    /**
     * 终审工作量（人天）
     */
    private Double lastReviewWorkLoad;

    /**
     * 预估金额（元）
     */
    private Double planAmount;
    /**
     * 单价（元）
     */
    private Double price;
    /**
     * 评审情况
     */
    private String reviewResult;
    /**
     * 需求提出时间
     */
    private Date submitTime;
    /**
     * 评审时间
     */
    private Date reviewTime;

    /**
     * 评审时长
     */
    private Double reviewDuration;

    public RequirementReview(String reviewId, String requirementId) {
        this.reviewId = reviewId;
        this.requirementId = requirementId;
    }
}
