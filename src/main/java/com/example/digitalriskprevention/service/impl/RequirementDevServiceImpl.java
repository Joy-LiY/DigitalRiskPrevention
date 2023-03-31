package com.example.digitalriskprevention.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.digitalriskprevention.mapper.RequirementDevMapper;
import com.example.digitalriskprevention.model.RequirementDev;
import com.example.digitalriskprevention.service.RequirementDevService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/23
 * @Description: 需求开发上线信息
 * @Version: 1.0
 */
@Service
@Slf4j
public class RequirementDevServiceImpl extends ServiceImpl<RequirementDevMapper, RequirementDev> implements RequirementDevService {
    /**
     * @param requirementDevList
     * @description: 分组批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:40
     * @param: [requirementDevList]
     * @return: void
     */
    @Override
    public void partitionSaveBatch(List<RequirementDev> requirementDevList) {
        if (CollectionUtils.isEmpty(requirementDevList)) {
            return;
        }

        List<List<RequirementDev>> lists = ListUtils.partition(requirementDevList, DEFAULT_BATCH_SIZE);
        lists.forEach(this::saveBatch);
    }

    /**
     * @param requirementIds
     * @description: 根据requirementIDs删除开发信息
     * @author: zhangwentao
     * @date: 2023/3/13 下午2:52
     * @param: [requirementIds]
     * @return: boolean
     */
    @Override
    public boolean removeByRequirementIds(List<String> requirementIds) {
        if (CollectionUtils.isEmpty(requirementIds)) {
            return false;
        }
        LambdaQueryWrapper<RequirementDev> devQueryWrapper = new LambdaQueryWrapper<>();
        devQueryWrapper.in(RequirementDev::getRequirementId, requirementIds);
        return this.remove(devQueryWrapper);
    }
}
