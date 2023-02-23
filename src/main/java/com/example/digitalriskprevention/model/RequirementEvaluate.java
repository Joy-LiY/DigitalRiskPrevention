package com.example.digitalriskprevention.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jetbrains.annotations.Contract;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/22
 * @Description: 需求后评估
 * @Version: 1.0
 */

@Data
@TableName(value = "requirement_evaluate")
public class RequirementEvaluate {
    /**
     * 评审表主键
     */
    @TableId
    private String evaluateId;
    /**
     * 关联需求基本信息的主键
     */
    private String requirementId;
    /**
     * 功能大类归属
     */
    private String functionType;
    /**
     * 页面响应时长（s）
     */
    private String requestTime;

    /**
     * 月均点击频次（次）
     */
    private String monthHits;

    /**
     * 月均使用人数（人）
     */
    private String userAmount;

    /**
     * 接口日均调用量（接口类需求）（次）
     */
    private String apiRequestAmount;

    /**
     * 使用较少的原因说明
     */
    private String reason;

    @Contract(pure = true)
    public RequirementEvaluate(String evaluateId, String requirementId) {
        this.evaluateId = evaluateId;
        this.requirementId = requirementId;
    }
}
