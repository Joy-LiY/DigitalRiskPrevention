package com.example.digitalriskprevention.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.digitalriskprevention.model.RequirementDev;

import java.util.List;

public interface RequirementDevService extends IService<RequirementDev> {
    /**
     * @description: 分组批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:40
     * @param: [requirementDevList]
     * @return: void
     **/
    void partitionSaveBatch(List<RequirementDev> requirementDevList);

    /**
     * @description: 根据requirementIDs删除开发信息
     * @author: zhangwentao
     * @date: 2023/3/13 下午2:52
     * @param: [requirementIds]
     * @return: boolean
     **/
    boolean removeByRequirementIds(List<String> requirementIds);
}
