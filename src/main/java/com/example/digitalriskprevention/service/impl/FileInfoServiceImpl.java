package com.example.digitalriskprevention.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.digitalriskprevention.mapper.FileInfoMapper;
import com.example.digitalriskprevention.model.FileInfo;
import com.example.digitalriskprevention.service.FileInfoService;
import com.example.digitalriskprevention.utils.CusAccessObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/23
 * @Description: 记录上传的文件信息
 * @Version: 1.0
 */
@Service
@Slf4j
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {
    /**
     * 成功
     */
    private final static Integer SUCCESS = 1;
    /**
     * 失败
     */
    private final static Integer FAIL = 0;

    /**
     * @param fileInfo
     * @param file
     * @param result
     * @param request
     * @description: 保存上传的文件信息
     * @author: zhangwentao
     * @date: 2023/2/23 上午11:06
     * @param: [file, result]
     * @return: void
     */
    @Override
    public void saveFileInfo(FileInfo fileInfo, MultipartFile file, boolean result, HttpServletRequest request) {
        if (StringUtils.isBlank(fileInfo.getId())) {
            return;
        }
        // 设置上传状态
        if (result) {
            fileInfo.setStatus(SUCCESS);
        } else {
            fileInfo.setStatus(FAIL);
        }
        fileInfo.setFileName(file.getOriginalFilename());
        fileInfo.setSize(file.getSize());
        fileInfo.setUploadDate(new DateTime());
        fileInfo.setIp(CusAccessObjectUtil.getIpAddress(request));

        this.save(fileInfo);
    }
}
