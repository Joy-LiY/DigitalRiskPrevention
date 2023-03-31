package com.example.digitalriskprevention.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.digitalriskprevention.model.RequirementDev;

import java.util.Date;

/**
 * 开发上线及时率
 */
public interface DealRequirementDevService extends IService<RequirementDev> {

    /**
     * 开发上线率（实际上线时间-预计上线时间）
     * @param planTime 计划上线时间
     * @param realTime 预计上线时间
     * @return
     */
    int dealLaunchTime(Date planTime, Date realTime);

}
