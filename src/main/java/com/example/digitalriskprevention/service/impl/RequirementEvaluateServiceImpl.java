package com.example.digitalriskprevention.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.digitalriskprevention.mapper.RequirementEvaluateMapper;
import com.example.digitalriskprevention.model.RequirementEvaluate;
import com.example.digitalriskprevention.service.RequirementEvaluateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/23
 * @Description: 后评估信息
 * @Version: 1.0
 */
@Slf4j
@Service
public class RequirementEvaluateServiceImpl extends ServiceImpl<RequirementEvaluateMapper, RequirementEvaluate> implements RequirementEvaluateService {
    /**
     * @param requirementEvaluateList
     * @description: 分组批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:40
     * @param: [requirementDevList]
     * @return: void
     */
    @Override
    public void partitionSaveBatch(List<RequirementEvaluate> requirementEvaluateList) {
        if (CollectionUtils.isEmpty(requirementEvaluateList)) {
            return;
        }

        List<List<RequirementEvaluate>> lists = ListUtils.partition(requirementEvaluateList, DEFAULT_BATCH_SIZE);
        lists.forEach(this::saveBatch);
    }

    /**
     * @param requirementIds
     * @description: 根据外健批量删除
     * @author: zhangwentao
     * @date: 2023/3/13 下午2:57
     * @param: [requirementIds]
     * @return: boolean
     */
    @Override
    public boolean removeByRequirementIds(List<String> requirementIds) {
        if (CollectionUtils.isEmpty(requirementIds)) {
            return false;
        }
        LambdaQueryWrapper<RequirementEvaluate> evaluateQueryWrapper = new LambdaQueryWrapper<>();
        evaluateQueryWrapper.in(RequirementEvaluate::getRequirementId, requirementIds);
        return this.remove(evaluateQueryWrapper);
    }
}
