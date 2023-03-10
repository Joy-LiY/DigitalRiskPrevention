package com.example.digitalriskprevention.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.digitalriskprevention.model.RequirementEvaluate;

import java.util.List;

public interface RequirementEvaluateService extends IService<RequirementEvaluate> {
    /**
     * @description: 分组批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:40
     * @param: [requirementDevList]
     * @return: void
     **/
    void partitionSaveBatch(List<RequirementEvaluate> requirementEvaluateList);

    /**
     * @description: 根据外健批量删除
     * @author: zhangwentao
     * @date: 2023/3/13 下午2:57
     * @param: [requirementIds]
     * @return: boolean
     **/
    boolean removeByRequirementIds(List<String> requirementIds);
}
