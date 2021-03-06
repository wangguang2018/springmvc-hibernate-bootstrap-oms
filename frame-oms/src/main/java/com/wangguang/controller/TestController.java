package com.wangguang.controller;

import com.wangguang.dao.RoleRepository;
import com.wangguang.model.sys.Menu;
import com.wangguang.model.sys.Role;
import com.wangguang.model.sys.User;
import com.wangguang.service.AccountService;
import com.wangguang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionAttributes("user")
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    MenuService menuService;

    @Autowired
    AccountService accountService;

    @Autowired
    RoleRepository roleRepository;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(){
        Menu menu = menuService.findMenu(8);
        User user = accountService.getUser(1);
        List<Role> roles = user.getRoleList();
        Role role = roleRepository.findOne(1);
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
