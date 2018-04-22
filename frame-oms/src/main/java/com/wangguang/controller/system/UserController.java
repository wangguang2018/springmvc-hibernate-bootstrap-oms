package com.wangguang.controller.system;

import com.wangguang.common.bo.Constant;
import com.wangguang.common.vo.Pagination;
import com.wangguang.controller.common.WebController;
import com.wangguang.model.sys.Role;
import com.wangguang.model.sys.User;
import com.wangguang.service.AccountService;
import com.wangguang.service.RoleService;
import com.wangguang.web.JsonMap;
import com.wangguang.web.Servlets;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Controller - 系统用户
 */
@Controller
public class UserController extends WebController {

    @Resource
    private AccountService accountService;

    @Resource
    private RoleService roleService;

    /**
     * 系统用户列表页
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "user/list";
    }

    /**
     * 系统用户列表数据
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_deleted", false);
        model.addAttribute("page", accountService.list(pagination, searchParams));
        return "user/nested";
    }

    /**
     * 创建新用户
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String add(Model model) {
        List<Role> roles = roleService.findRoleList();
        model.addAttribute("roles", roles);
        return "user/edit";
    }

    /**
     * 编辑用户
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {
        User user = accountService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findRoleList());
        return "user/edit";
    }

    /**
     * 删除用户
     */
    @ResponseBody
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        accountService.delete(ids);
        return new JsonMap(0, "删除成功");
    }

    /**
     * 用户禁用
     */
    @ResponseBody
    @RequestMapping(value = "/user/disable", method = RequestMethod.POST)
    public JsonMap disable(@RequestParam("id[]") Integer[] ids) {
        accountService.updateStatus(ids, Constant.Status.DISABLE);
        return new JsonMap(0, "禁用成功");
    }

    /**
     * 用户解禁
     */
    @ResponseBody
    @RequestMapping(value = "/user/enable", method = RequestMethod.POST)
    public JsonMap enable(@RequestParam("id[]") Integer[] ids) {
        accountService.updateStatus(ids, Constant.Status.Enable);
        return new JsonMap(0, "解禁成功");
    }

    /**
     * 检验账号是否存在
     */
    @ResponseBody
    @RequestMapping(value = "/user/account/exists", method = RequestMethod.GET)
    public Boolean accountExists(String account, Integer userId) {
        return !accountService.existsAccount(userId, account);
    }

    /**
     * 检验手机号码是否存在
     */
    @ResponseBody
    @RequestMapping(value = "/user/mobile/exists", method = RequestMethod.GET)
    public Boolean mobileExists(Integer userId, String mobile) {
        return !accountService.existsMobile(userId, mobile);
    }

    /**
     * 邮箱是否存在
     */
    @ResponseBody
    @RequestMapping(value = "/user/email/exists", method = RequestMethod.GET)
    public Boolean emailExists(Integer userId, String email) {
        return !accountService.existsEmail(userId, email);
    }

    /**
     * 保存用户
     */
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public JsonMap save(@Valid User user, BindingResult result) {
        JsonMap ret;
        if (result.hasErrors()) {
            ret = parseErrorResult(result, "保存失败");
        } else {
            accountService.save(user);
            ret = new JsonMap(0, "保存成功");
        }
        return ret;
    }
}
