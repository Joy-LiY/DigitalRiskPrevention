package com.example.digitalriskprevention.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.digitalriskprevention.mapper.RequirementMapper;
import com.example.digitalriskprevention.model.*;
import com.example.digitalriskprevention.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/22
 * @Description: 需求基础信息
 * @Version: 1.0
 */
@Slf4j
@Service
public class RequirementServiceImpl extends ServiceImpl<RequirementMapper, Requirement> implements RequirementService {
    @Resource
    private RequirementReviewService requirementReviewService;
    @Resource
    private RequirementDevService requirementDevService;
    @Resource
    private RequirementCheckService requirementCheckService;
    @Resource
    private RequirementEvaluateService requirementEvaluateService;

    /**
     * @param file
     * @description: 上传需求Excel信息，导入数据库
     * @author: zhangwentao
     * @date: 2023/2/22 下午2:54
     * @param: [file]
     * @return: java.lang.String
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean importFile(@NotNull MultipartFile file) throws IOException {
        // 定义需求基本信息
        List<Requirement> requirementList = new ArrayList<>();
        // 定义需求评审情况
        List<RequirementReview> requirementReviewList = new ArrayList<>();
        // 定义需求开发上线信息
        List<RequirementDev> requirementDevList = new ArrayList<>();
        // 定义需求验收
        List<RequirementCheck> requirementCheckList = new ArrayList<>();
        // 定义需求后评估
        List<RequirementEvaluate> requirementEvaluateList = new ArrayList<>();

        ExcelUtil.readBySax(file.getInputStream(), 0, createRowHandler(requirementList, requirementReviewList, requirementDevList, requirementCheckList, requirementEvaluateList));
        // 批量保存,对列表每1000条进行一次切片
        this.partitionSaveBatch(requirementList);
        requirementReviewService.partitionSaveBatch(requirementReviewList);
        requirementDevService.partitionSaveBatch(requirementDevList);
        requirementCheckService.partitionSaveBatch(requirementCheckList);
        requirementEvaluateService.partitionSaveBatch(requirementEvaluateList);
        return true;
    }

    /**
     * @description: 大数据量时，对数据先分片再批量保存
     * @author: zhangwentao
     * @date: 2023/2/23 上午9:36
     * @param: [requirementList]
     * @return: void
     **/
    @Override
    public void partitionSaveBatch(List<Requirement> requirementList) {
        if (CollectionUtils.isEmpty(requirementList)) {
            return;
        }

        List<List<Requirement>> lists = ListUtils.partition(requirementList, DEFAULT_BATCH_SIZE);
        lists.forEach(this::saveBatch);
    }

    /**
     * 对每行数据进行处理
     *
     * @param requirementList
     * @param requirementReviewList
     * @param requirementDevList
     * @param requirementCheckList
     * @param requirementEvaluateList
     * @return
     */
    private RowHandler createRowHandler(List<Requirement> requirementList, List<RequirementReview> requirementReviewList, List<RequirementDev> requirementDevList, List<RequirementCheck> requirementCheckList, List<RequirementEvaluate> requirementEvaluateList) {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, long rowIndex, List<Object> rowlist) {
                Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowlist);
                // 模版的第一行和第二行是标题
                if (rowIndex == 0 || rowIndex == 1) {
                    return;
                }
                // 主表主键
                String mainId = IdUtil.getSnowflakeNextIdStr();
                Requirement requirement = new Requirement(mainId);
                RequirementReview requirementReview = new RequirementReview(IdUtil.getSnowflakeNextIdStr(), mainId);
                RequirementDev requirementDev = new RequirementDev(IdUtil.getSnowflakeNextIdStr(), mainId);
                RequirementCheck requirementCheck = new RequirementCheck(IdUtil.getSnowflakeNextIdStr(), mainId);
                RequirementEvaluate requirementEvaluate = new RequirementEvaluate(IdUtil.getSnowflakeNextIdStr(), mainId);

                // 遍历行数据,一共36列数据
                for (int i = 0; i < 36; i++) {
                    Object cellObject = rowlist.get(i);
                    if (cellObject == null) {
                        continue;
                    }
                    this.initRequirement(i, cellObject, requirement);
                    this.initRequirementReview(i, cellObject, requirementReview);
                    this.initRequirementDev(i, cellObject, requirementDev);
                    this.initRequirementCheck(i, cellObject, requirementCheck);
                    this.initRequirementEvaluate(i, cellObject, requirementEvaluate);
                }

                requirementList.add(requirement);
                requirementReviewList.add(requirementReview);
                requirementDevList.add(requirementDev);
                requirementCheckList.add(requirementCheck);
                requirementEvaluateList.add(requirementEvaluate);

            }


            /**
             * 封装需求后评估信息
             *
             * @param index
             * @param cellObject
             * @param requirementEvaluate
             */
            private void initRequirementEvaluate(int index, Object cellObject, RequirementEvaluate requirementEvaluate) {
                switch (index) {
                    case 30:
                        requirementEvaluate.setFunctionType(cellObject.toString());
                        break;
                    case 31:
                        requirementEvaluate.setRequestTime(cellObject.toString());
                        break;
                    case 32:
                        requirementEvaluate.setMonthHits(cellObject.toString());
                        break;
                    case 33:
                        requirementEvaluate.setUserAmount(cellObject.toString());
                        break;
                    case 34:
                        requirementEvaluate.setApiRequestAmount(cellObject.toString());
                        break;
                    case 35:
                        requirementEvaluate.setReason(cellObject.toString());
                        break;
                    default:
                        return;
                }
            }

            /**
             * 封装需求验收信息
             *
             * @param index
             * @param cellObject
             * @param requirementCheck
             */
            private void initRequirementCheck(int index, Object cellObject, RequirementCheck requirementCheck) {
                switch (index) {
                    // 需求验收
                    case 25:
                        requirementCheck.setCheckStatus(cellObject.toString());
                        break;
                    case 26:
                        requirementCheck.setCheckTime(DateUtil.parseDateTime(cellObject.toString()));
                        break;
                    case 27:
                        requirementCheck.setCheckDuration(Double.parseDouble(cellObject.toString()));
                        break;
                    case 28:
                        requirementCheck.setIsOvertime(cellObject.toString());
                        break;
                    case 29:
                        requirementCheck.setReason(cellObject.toString());
                        break;
                    default:
                        return;

                }
            }

            /**
             * 封装需求开发数据
             *
             * @param index
             * @param cellObject
             * @param requirementDev
             */
            private void initRequirementDev(int index, Object cellObject, RequirementDev requirementDev) {
                switch (index) {
                    // 需求开发
                    case 19:
                        requirementDev.setPlanTime(DateUtil.parseDate(cellObject.toString()));
                        break;
                    case 20:
                        requirementDev.setRealTime(DateUtil.parseDate(cellObject.toString()));
                        break;
                    case 21:
                        requirementDev.setDevDuration(Double.parseDouble(cellObject.toString()));
                        break;
                    case 22:
                        requirementDev.setIsOvertime(cellObject.toString());
                        break;
                    case 23:
                        requirementDev.setDevStatus(cellObject.toString());
                        break;
                    case 24:
                        requirementDev.setReason(cellObject.toString());
                        break;
                    default:
                        return;
                }
            }


            /**
             * 封装需求评审信息
             *
             * @param index             每行数据中的索引
             * @param cellObject
             * @param requirementReview
             */
            private void initRequirementReview(int index, Object cellObject, RequirementReview requirementReview) {
                switch (index) {
                    case 5:
                        requirementReview.setDescription(cellObject.toString());
                        break;
                    case 6:
                        requirementReview.setType(cellObject.toString());
                        break;
                    case 7:
                        requirementReview.setLeader(cellObject.toString());
                        break;
                    case 8:
                        requirementReview.setPresenter(cellObject.toString());
                        break;
                    case 9:
                        requirementReview.setDepartment(cellObject.toString());
                        break;
                    case 10:
                        requirementReview.setPlanWorkload(Double.parseDouble(cellObject.toString()));
                        break;
                    case 11:
                        requirementReview.setFirstReviewWorkload(Double.parseDouble(cellObject.toString()));
                        break;
                    case 12:
                        requirementReview.setLastReviewWorkLoad(Double.parseDouble(cellObject.toString()));
                        break;
                    case 13:
                        requirementReview.setPlanAmount(Double.parseDouble(cellObject.toString()));
                        break;
                    case 14:
                        requirementReview.setPrice(Double.parseDouble(cellObject.toString()));
                        break;
                    case 15:
                        requirementReview.setReviewResult(cellObject.toString());
                        break;
                    case 16:
                        requirementReview.setSubmitTime(DateUtil.parseDate(cellObject.toString()));
                        break;
                    case 17:
                        requirementReview.setReviewTime(DateUtil.parseDateTime(cellObject.toString()));
                        break;
                    case 18:
                        requirementReview.setReviewDuration(Double.parseDouble(cellObject.toString()));
                        break;
                    default:
                        return;
                }
            }

            /**
             * 封装需求基础信息
             *
             * @param index       每行数据中的索引
             * @param cellObject
             * @param requirement
             */
            private void initRequirement(int index, Object cellObject, Requirement requirement) {
                switch (index) {
                    // 基本信息
                    case 0:
                        requirement.setOrder(Integer.parseInt(cellObject.toString()));
                        break;
                    case 1:
                        requirement.setFactory(cellObject.toString());
                        break;
                    case 2:
                        requirement.setHomeSystem(cellObject.toString());
                        break;
                    case 3:
                        requirement.setNumber(cellObject.toString());
                        break;
                    case 4:
                        requirement.setTitle(cellObject.toString());
                        break;
                    default:
                        return;
                }
            }
        };
    }
}
