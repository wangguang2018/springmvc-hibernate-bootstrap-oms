package com.wangguang.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wangguang.entity.sys.Menu;
import com.wangguang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@SessionAttributes("user")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    MenuService menuService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelAndView modelAndView){
        List<Menu> rootMenuList = Lists.newArrayList();
        Menu menu = new Menu(1, "系统管理", "", "fa fa-cogs", null);
        Set<Menu> childMenuList = Sets.newHashSet();
        childMenuList.add(new Menu(2, "用户管理", "users", "fa fa-cogs", menu));
        childMenuList.add(new Menu(3, "菜单管理", "menus", "", menu));
        childMenuList.add(new Menu(4, "角色管理", "roles", "fs", menu));
        rootMenuList.add(menu);
        modelAndView.addObject("menus", rootMenuList);
        modelAndView.addObject("childMenus", childMenuList);
        modelAndView.addObject("activeRootMenuId", 1);
        modelAndView.addObject("activeMenuId", 2);
        return "user/list";
    }


    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }

    @RequestMapping("/info")
    public String info(){
        return "user/success";
    }

    @RequestMapping("/findall")
    @ResponseBody
    public Map<String, Object> getUser(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
//        map.put("data", userService.findAll());
        return map;
    }

    @RequestMapping("/findbyid")
    @ResponseBody
    public Map<String, Object> findById(Integer id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
//        map.put("data", userService.findById(id));
        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> save(String name){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
//        map.put("data", userService.save(name));
        return map;
    }



}
