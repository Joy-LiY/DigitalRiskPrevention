package com.example.digitalriskprevention.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/22
 * @Description: 需求验收
 * @Version: 1.0
 */
@Data
@TableName(value = "requirement_check")
public class RequirementCheck implements Serializable {
    private static final long serialVersionUID = -4598451898211596584L;
    /**
     * 需求验收主键ID
     */
    @TableId
    private String checkId;

    /**
     * 关联需求基本信息的主键
     */
    private String requirementId;

    /**
     * 验收状态
     */
    private String checkStatus;

    /**
     * 验收时间
     */
    private Date checkTime;

    /**
     * 验收时长
     */
    private Double checkDuration;

    /**
     * 是否超时
     */
    private String isOvertime;

    /**
     * 原因（超时及未验收）
     */
    private String reason;

    public RequirementCheck(String checkId, String requirementId) {
        this.checkId = checkId;
        this.requirementId = requirementId;

    }
}
