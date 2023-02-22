package com.example.digitalriskprevention.service.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.digitalriskprevention.mapper.RequirementMapper;
import com.example.digitalriskprevention.model.Requirement;
import com.example.digitalriskprevention.service.RequirementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/22
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Service
public class RequirementServiceImpl extends ServiceImpl<RequirementMapper, Requirement> implements RequirementService {
    /**
     * @param file
     * @description: 上传需求Excel信息，导入数据库
     * @author: zhangwentao
     * @date: 2023/2/22 下午2:54
     * @param: [file]
     * @return: java.lang.String
     */
    @Override
    public String importFile(@NotNull MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        reader = this.callExcelHeader(reader);
//        List<CallRecordList> recordLists = reader.readAll(CallRecordList.class);

        // 生成CallRecord信息
        String fileName = StringUtils.split(file.getOriginalFilename(),".")[0];
//        return  this.saveRecord(fileName,recordLists);
        return null;
    }


    /**
     * 设置话单标题映射，直接生成bean
     * @param reader
     * @return
     */
    private ExcelReader callExcelHeader(@NotNull ExcelReader reader) {
        reader.addHeaderAlias("通话时间", "callDate");
        reader.addHeaderAlias("通话地点", "callPlace");
        reader.addHeaderAlias("通信方式", "callMode");
        reader.addHeaderAlias("对方号码", "targetPhone");
        reader.addHeaderAlias("基站号", "baseStationNum");
        reader.addHeaderAlias("位置小区号", "cellNum");
        reader.addHeaderAlias("状态类型", "stateType");
        reader.addHeaderAlias("网络类型", "networkType");
        reader.addHeaderAlias("使用者", "userPhone");
        reader.addHeaderAlias("IMEI", "imei");

        return reader;
    }
}
