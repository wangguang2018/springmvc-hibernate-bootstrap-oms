package com.wangguang.controller;

import com.wangguang.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoodsController {

    @RequestMapping("/goods/detail")
    public String detail(Model model){
        return "/goods/detail";
    }
}
