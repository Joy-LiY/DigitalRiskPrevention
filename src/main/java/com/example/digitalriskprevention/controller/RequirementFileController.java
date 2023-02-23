package com.example.digitalriskprevention.controller;


import cn.hutool.core.util.IdUtil;
import com.example.digitalriskprevention.model.FileInfo;
import com.example.digitalriskprevention.service.FileInfoService;
import com.example.digitalriskprevention.service.RequirementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 需求excel 文件上传
 * @author wangzai
 */

@RestController
@RequestMapping("/requirement/file")
@Slf4j
public class RequirementFileController {
    @Resource
    private RequirementService requirementService;
    @Resource
    private FileInfoService fileInfoService;

    @PostMapping("/import")
    public boolean importFile(MultipartFile file, HttpServletRequest request) throws IOException {
        boolean result = true;
        // 不要改变这个代码的位置
        FileInfo fileInfo = new FileInfo(IdUtil.getSnowflakeNextIdStr());
        try {
            result = requirementService.importFile(file, fileInfo);
        } catch (Exception e) {
            log.error("数据导入异常:{}", e.getMessage());
            result = false;
        } finally {
            // 记录文件保存内容
            fileInfoService.saveFileInfo(fileInfo, file, result, request);
        }
        return result;
    }


}
