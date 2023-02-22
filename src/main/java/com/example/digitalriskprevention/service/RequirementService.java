package com.example.digitalriskprevention.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.digitalriskprevention.model.Requirement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author taozi
 */
public interface RequirementService extends IService<Requirement>{
    /**
     * @description: 上传需求Excel信息，导入数据库
     * @author: zhangwentao
     * @date: 2023/2/22 下午2:54
     * @param: [file]
     * @return: java.lang.String
     **/
    String importFile(MultipartFile file) throws IOException;
}
