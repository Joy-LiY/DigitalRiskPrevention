package com.example.digitalriskprevention.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.digitalriskprevention.model.FileInfo;
import com.example.digitalriskprevention.model.Requirement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author taozi
 */
public interface RequirementService extends IService<Requirement> {
    /**
     * @description: 上传需求Excel信息，导入数据库
     * @author: zhangwentao
     * @date: 2023/2/22 下午2:54
     * @param: [file]
     * @return: java.lang.String
     **/
    boolean importFile(MultipartFile file, FileInfo fileInfo) throws IOException;

    /**
     * @description: 分组批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:40
     * @param: [requirementDevList]
     * @return: void
     **/
    void partitionSaveBatch(List<Requirement> requirementList);
}
