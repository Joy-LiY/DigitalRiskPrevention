package com.example.digitalriskprevention.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.digitalriskprevention.mapper.RequirementReviewMapper;
import com.example.digitalriskprevention.model.RequirementReview;
import com.example.digitalriskprevention.service.RequirementReviewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/23
 * @Description: 需求评审信息
 * @Version: 1.0
 */
@Service
@Slf4j
public class RequirementReviewServiceImpl extends ServiceImpl<RequirementReviewMapper, RequirementReview> implements RequirementReviewService {
    /**
     * @param requirementReviewList
     * @description: 分组批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:40
     * @param: [requirementReviewList]
     * @return: void
     */
    @Override
    public void partitionSaveBatch(List<RequirementReview> requirementReviewList) {
        if (CollectionUtils.isEmpty(requirementReviewList)) {
            return;
        }

        List<List<RequirementReview>> lists = ListUtils.partition(requirementReviewList, DEFAULT_BATCH_SIZE);
        lists.forEach(this::saveBatch);
    }
}
