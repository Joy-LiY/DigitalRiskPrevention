package com.example.digitalriskprevention.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.digitalriskprevention.model.RequirementReview;

import java.util.List;

public interface RequirementReviewService extends IService<RequirementReview> {
    /**
     * @description: 分组批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:40
     * @param: [requirementReviewList]
     * @return: void
     **/
    void partitionSaveBatch(List<RequirementReview> requirementReviewList);

}
