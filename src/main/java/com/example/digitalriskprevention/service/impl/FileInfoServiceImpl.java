package com.example.digitalriskprevention.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.digitalriskprevention.mapper.FileInfoMapper;
import com.example.digitalriskprevention.model.FileInfo;
import com.example.digitalriskprevention.model.Requirement;
import com.example.digitalriskprevention.model.qo.FileInfoQO;
import com.example.digitalriskprevention.service.*;
import com.example.digitalriskprevention.utils.CusAccessObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/23
 * @Description: 记录上传的文件信息
 * @Version: 1.0
 */
@Service
@Slf4j
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {
    @Resource
    private RequirementService requirementService;
    @Resource
    private RequirementReviewService requirementReviewService;
    @Resource
    private RequirementDevService requirementDevService;
    @Resource
    private RequirementCheckService requirementCheckService;
    @Resource
    private RequirementEvaluateService requirementEvaluateService;

    /**
     * 成功
     */
    public static final Integer SUCCESS = 1;
    /**
     * 失败
     */
    public static final Integer FAIL = 0;
    /**
     * 部分成功
     */
    public static final Integer PART = 2;

    /**
     * @param fileInfo
     * @param file
     * @param request
     * @description: 保存上传的文件信息
     * @author: zhangwentao
     * @date: 2023/2/23 上午11:06
     * @param: [file, result]
     * @return: void
     */
    @Override
    public void saveFileInfo(FileInfo fileInfo, MultipartFile file, HttpServletRequest request) {
        if (StringUtils.isBlank(fileInfo.getId())) {
            return;
        }
        fileInfo.setFileName(file.getOriginalFilename());
        fileInfo.setSize(file.getSize());
        fileInfo.setUploadDate(new DateTime());
        fileInfo.setIp(CusAccessObjectUtil.getIpAddress(request));

        this.save(fileInfo);
    }

    /**
     * 文件信息分页查询
     *
     * @param
     * @param fileInfoQO 分页查询对象
     * @return
     */
    @Override
    public IPage<FileInfo> getFileInfoPage(FileInfoQO fileInfoQO) {
        Page<FileInfo> fileInfoPage = new Page<>(fileInfoQO.getCurrent(), fileInfoQO.getSize());

        LambdaQueryWrapper<FileInfo> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(fileInfoQO.getFileName())) {
            queryWrapper.like(FileInfo::getFileName, fileInfoQO.getFileName());
        }
        if (null != fileInfoQO.getBeginDate() && null != fileInfoQO.getEndDate()) {
            queryWrapper.between(FileInfo::getUploadDate, fileInfoQO.getBeginDate(), fileInfoQO.getEndDate());
        }
        queryWrapper.orderByDesc(FileInfo::getUploadDate);
        fileInfoPage = this.baseMapper.selectPage(fileInfoPage, queryWrapper);
        return fileInfoPage;
    }

    /**
     * @param fileId
     * @description: 根据文件ID删除所有导入的数据
     * @author: zhangwentao
     * @date: 2023/3/13 下午2:10
     * @param: [fileId]
     * @return: boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delFileData(String fileId) {
        if (StringUtils.isBlank(fileId)) {
            return false;
        }
        // 获取文件对应的requirement 的主键ID
        LambdaQueryWrapper<Requirement> requirementQueryWrapper = new LambdaQueryWrapper<>();
        requirementQueryWrapper.ge(Requirement::getId, fileId);
        List<String> requirementIds = requirementService.list(requirementQueryWrapper).stream().map(Requirement::getId).collect(Collectors.toList());

        //删除需求评审信息
        requirementReviewService.removeByRequirementIds(requirementIds);

        // 删除需求开发信息
        requirementDevService.removeByRequirementIds(requirementIds);

        // 删除需求验收信息
        requirementCheckService.removeByRequirementIds(requirementIds);

        // 删除需求后评估信息
        requirementEvaluateService.removeByRequirementIds(requirementIds);

        // 删除需求表
        requirementService.removeByFileId(fileId);

        // 删除文件信息
        return this.removeById(fileId);
    }
}
