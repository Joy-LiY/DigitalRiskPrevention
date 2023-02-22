package com.example.digitalriskprevention.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/22
 * @Description: 用于处理页面跳转的Controller
 * @Version: 1.0
 */
@Controller
@RequestMapping("")
public class PageController {

    @RequestMapping("/file/upload")
    public String fileUpload() {
        return "fileupload";
    }
}
