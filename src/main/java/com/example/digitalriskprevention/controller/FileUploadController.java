package com.example.digitalriskprevention.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * excel 文件上传
 * @author  wangzai
 */

@Controller
@RequestMapping("/")
public class FileUploadController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
