package com.example.digitalriskprevention.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.digitalriskprevention.common.Result;
import com.example.digitalriskprevention.model.FileInfo;
import com.example.digitalriskprevention.model.qo.FileInfoQO;
import com.example.digitalriskprevention.service.FileInfoService;
import com.example.digitalriskprevention.service.RequirementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 需求excel 文件上传
 *
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

    /**
     * Excel数据导入
     *
     * @param file
     * @param request
     * @return Integer  数据导入状态
     * @throws IOException
     */
    @PostMapping("/import")
    public Integer importFile(MultipartFile file, HttpServletRequest request) throws IOException {
        boolean result = true;
        // 不要改变这个代码的位置
        FileInfo fileInfo = new FileInfo(IdUtil.getSnowflakeNextIdStr());
        try {
            fileInfo = requirementService.importFile(file, fileInfo);
        } catch (Exception e) {
            log.error("数据导入异常:{}", e);
            result = false;
        } finally {
            // 记录文件保存内容
            fileInfoService.saveFileInfo(fileInfo, file, result, request);
        }
        return fileInfo.getStatus();
    }

    @PostMapping("/page")
    public Result getFileInfoPage(@RequestBody FileInfoQO fileInfoQO) {
        IPage<FileInfo> fileInfoPage = fileInfoService.getFileInfoPage(fileInfoQO);
        return Result.ok(fileInfoPage.getRecords(), (int) fileInfoPage.getTotal());
    }
}
