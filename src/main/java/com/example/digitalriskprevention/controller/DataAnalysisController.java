package com.example.digitalriskprevention.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据表分析
 * @author ljx
 */
@Controller
@RequestMapping("/DataAnalysis")
public class DataAnalysisController {

    @RequestMapping("/form")
    public String getFormData(Model model) {
        model.addAttribute("","");
        return "index";
    }

}
