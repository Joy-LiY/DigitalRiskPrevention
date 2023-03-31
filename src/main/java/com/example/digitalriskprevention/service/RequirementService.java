package com.example.digitalriskprevention.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.digitalriskprevention.model.FileInfo;
import com.example.digitalriskprevention.model.Requirement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author taozi
 */
public interface RequirementService extends IService<Requirement> {
    /**
     * @description: 上传需求Excel信息，导入数据库
     * @author: zhangwentao
     * @date: 2023/2/22 下午2:54
     * @param: [file]
     * @return: FileInfo
     **/
    FileInfo importFile(MultipartFile file, FileInfo fileInfo) throws IOException;

    /**
     * @description: 分组批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:40
     * @param: [requirementDevList]
     * @return: void
     **/
    void partitionSaveBatch(List<Requirement> requirementList);

    /**
     * @description: 根据文件Id批量删除数据
     * @author: zhangwentao
     * @date: 2023/3/13 下午2:59
     * @param: [fileId]
     * @return: boolean
     **/
    boolean removeByFileId(String fileId);

    /**
     * @description: 按照厂商进行需求划分
     * @author: ljx
     * @return
     */
    List<Map<String,Object>> getAllRequirementList();

    /**
     *  @description: 按照厂商需求超时率
     *  @author: ljx
     * @return
     */
    Map<String,Double> getRequirementisOvertime();

    List<List<Map<String,Object>>> getRequirementsRequestAmounts();

    List<List<Object>> getRequirementSimilar();
}
