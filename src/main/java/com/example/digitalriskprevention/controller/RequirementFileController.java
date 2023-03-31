package com.example.digitalriskprevention.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.digitalriskprevention.common.Result;
import com.example.digitalriskprevention.model.FileInfo;
import com.example.digitalriskprevention.model.qo.FileInfoQO;
import com.example.digitalriskprevention.service.FileInfoService;
import com.example.digitalriskprevention.service.RequirementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
        // 不要改变这个代码的位置
        FileInfo fileInfo = new FileInfo(IdUtil.getSnowflakeNextIdStr());
        try {
            fileInfo = requirementService.importFile(file, fileInfo);
        } catch (Exception e) {
            log.error("数据导入异常:{}", e);
        } finally {
            // 记录文件保存内容
            fileInfoService.saveFileInfo(fileInfo, file, request);
        }
        return fileInfo.getStatus();
    }

    /**
     * 分页查询文件列表
     *
     * @param fileInfoQO
     * @return
     */
    @PostMapping("/page")
    public Result getFileInfoPage(@RequestBody FileInfoQO fileInfoQO) {
        IPage<FileInfo> fileInfoPage = fileInfoService.getFileInfoPage(fileInfoQO);
        return Result.ok(fileInfoPage.getRecords(), (int) fileInfoPage.getTotal());
    }

    /**
     * @description: 根据文件Id删除已经导入的数据信息
     * @author: zhangwentao
     * @date: 2023/3/13 下午2:06
     * @param: [fileId]
     * @return: com.example.digitalriskprevention.common.Result
     **/
    @DeleteMapping("/{fileId}")
    public Result delFileData(@PathVariable(value = "fileId") String fileId) {
        boolean result = fileInfoService.delFileData(fileId);
        return Result.ok(result);
    }

    /**
     * @description: 根据文件ID查询文件信息
     * @author: zhangwentao
     * @date: 2023/3/13 下午3:06
     * @param: [fileId]
     * @return: com.example.digitalriskprevention.common.Result
     **/
    @GetMapping("/{fileId}")
    public Result getFileInfo(@PathVariable(value = "fileId") String fileId) {
        FileInfo fileInfo = fileInfoService.getById(fileId);
        return Result.ok(fileInfo);
    }
}
