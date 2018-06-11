package com.wangguang.controller;

import com.google.common.collect.Lists;
import com.wangguang.controller.common.WebController;
import com.wangguang.service.MenuService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* author : douya
* date   : 2018-04-02 18:07
* desc   : 筛选框
**/
@Controller
@RequestMapping("/select")
public class SelectController extends WebController {

    private Logger logger = Logger.getLogger(SelectController.class);


    @Resource
    private MenuService menuService;

    @ResponseBody
    @RequestMapping(value = "/getSonMenus", method = RequestMethod.GET)
    public List<Map<String, Object>> getMenus() {
        List<Map<String, Object>> list = Lists.newArrayList();
        return menuService.menuList();
    }


}