package com.example.digitalriskprevention.controller;


import com.example.digitalriskprevention.service.RequirementService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 需求excel 文件上传
 * @author  wangzai
 */

@RestController
@RequestMapping("/requirement/file")
public class RequirementFileController {
    @Resource
    private RequirementService requirementService;

    @PostMapping("/import")
    public String importFile(MultipartFile file) throws IOException {
        return requirementService.importFile(file);
    }


}
