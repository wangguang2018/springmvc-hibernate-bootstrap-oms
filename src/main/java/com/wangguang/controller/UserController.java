package com.wangguang.controller;

import com.wangguang.dto.UserDto;
import com.wangguang.entity.User;
import com.wangguang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@SessionAttributes("user")
@Controller
public class UserController {

    @javax.annotation.Resource
    private UserService userService;

    @RequestMapping("/hello")
    public String helloWorld() throws IOException{
        Resource resource = new  ClassPathResource("/spring-mvcs.xml");
        byte[] byteArray = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return "user/success";
    }

    @RequestMapping("/user/save")
    public String save(User user,Model model){
       userService.save(user);
        return "user/success";
    }

    @RequestMapping("/user/map")
    @ResponseBody
    public List getMap(){
        List<UserDto> dtos = new ArrayList<UserDto>();
        UserDto userDto = new UserDto();
        userDto.setAge(11);
        dtos.add(userDto);
        if(1==1){
            throw new NullPointerException();
        }
        return dtos;
    }


    @RequestMapping("/user/byteArray")
    @ResponseBody
    public ResponseEntity<byte[]> getByteArray(){
        Resource resource = new  ClassPathResource("/spring-mvc.xml");
        byte[] byteArray = null;
        try {
            byteArray = FileCopyUtils.copyToByteArray(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("length:" + byteArray.length);
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(byteArray, HttpStatus.OK);
        return entity;
    }

    @RequestMapping("/edit")
    public String edit(){
        return "user/edit";
    }
}
