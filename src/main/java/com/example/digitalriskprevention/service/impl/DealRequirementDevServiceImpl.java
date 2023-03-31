package com.example.digitalriskprevention.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.digitalriskprevention.mapper.RequirementDevMapper;
import com.example.digitalriskprevention.model.RequirementDev;
import com.example.digitalriskprevention.service.DealRequirementDevService;

import java.util.Calendar;
import java.util.Date;

/**
 * 开发上线及时率
 */
public class DealRequirementDevServiceImpl extends ServiceImpl<RequirementDevMapper,RequirementDev> implements DealRequirementDevService {

    @Override
    public int dealLaunchTime(Date planTime, Date realTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(planTime);
        int planMonth = calendar.get(Calendar.MONTH);
        int planDay = calendar.get(Calendar.DATE);
        calendar.setTime(realTime);
        int realMonth = calendar.get(Calendar.MONTH);
        int realDay = calendar.get(Calendar.DATE);

        int projectLaunch = 0;
        if (planMonth == realMonth) {
            projectLaunch = realDay - planDay;
        }

        return projectLaunch;
    }

}
