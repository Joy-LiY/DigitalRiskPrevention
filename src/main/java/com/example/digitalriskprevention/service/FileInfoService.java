package com.example.digitalriskprevention.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.digitalriskprevention.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author taozi
 */
public interface FileInfoService extends IService<FileInfo> {
    /**
     * @description: 保存上传的文件信息
     * @author: zhangwentao
     * @date: 2023/2/23 上午11:06
     * @param: [file, result]
     * @return: void
     **/
    void saveFileInfo(FileInfo fileInfo, MultipartFile file, boolean result, HttpServletRequest request);
}
