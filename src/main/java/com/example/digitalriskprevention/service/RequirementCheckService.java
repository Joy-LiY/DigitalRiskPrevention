package com.example.digitalriskprevention.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.digitalriskprevention.model.RequirementCheck;

import java.util.List;

public interface RequirementCheckService extends IService<RequirementCheck> {
    /**
     * @description: 分组批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:40
     * @param: [requirementCheckList]
     * @return: void
     **/
    void partitionSaveBatch(List<RequirementCheck> requirementCheckList);
}
