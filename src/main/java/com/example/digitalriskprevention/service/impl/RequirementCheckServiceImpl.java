package com.example.digitalriskprevention.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.digitalriskprevention.mapper.RequirementCheckMapper;
import com.example.digitalriskprevention.model.RequirementCheck;
import com.example.digitalriskprevention.service.RequirementCheckService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/23
 * @Description: 需求验收信息
 * @Version: 1.0
 */
@Slf4j
@Service
public class RequirementCheckServiceImpl extends ServiceImpl<RequirementCheckMapper, RequirementCheck> implements RequirementCheckService {
    /**
     * @param requirementCheckList
     * @description: 分组批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:40
     * @param: [requirementCheckList]
     * @return: void
     */
    @Override
    public void partitionSaveBatch(List<RequirementCheck> requirementCheckList) {
        if (CollectionUtils.isEmpty(requirementCheckList)) {
            return;
        }

        List<List<RequirementCheck>> lists = ListUtils.partition(requirementCheckList, DEFAULT_BATCH_SIZE);
        lists.forEach(this::saveBatch);
    }
}
