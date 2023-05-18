package com.example.digitalriskprevention.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import com.example.digitalriskprevention.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 数据表分析
 * @author ljx
 */
@Controller
@RequestMapping("/")
public class DataAnalysisController {

    @Autowired
    RequirementService requirementService;

    @RequestMapping("index")
    public String index() {
        return "/maint";
    }

    /**
     * 厂商需求数量展示
     * @return
     */
    @RequestMapping("/getFoctoryNums")
    @ResponseBody
    public List<Map<String,Object>> getFormData() {
        return requirementService.getAllRequirementList();
    }

    @RequestMapping("/getRequirementisOvertime")
    @ResponseBody
    public Map<String,Double> getRequirementisOvertime() {
        return requirementService.getRequirementisOvertime();
    }

    @RequestMapping("/getRequirementsRequestAmount")
    @ResponseBody
    public List<List<Map<String,Object>>> getRequirementsRequestAmount() {
        return requirementService.getRequirementsRequestAmounts();
    }

    @RequestMapping("/getFaRequirementSimilar")
    @ResponseBody
    public List<List<Object>> getFaRequirementSimilar() {
        return  requirementService.getRequirementSimilar();
    }

}
