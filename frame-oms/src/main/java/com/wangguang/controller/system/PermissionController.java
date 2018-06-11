package com.wangguang.controller.system;



import com.wangguang.common.vo.Pagination;
import com.wangguang.controller.common.WebController;
import com.wangguang.model.sys.Menu;
import com.wangguang.model.sys.Permission;
import com.wangguang.service.PermissionService;
import com.wangguang.services.CommonService;
import com.wangguang.web.JsonMap;
import com.wangguang.web.Servlets;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller - 权限点
 *
 * @author xingkong1221
 * @date 2015-11-06
 */
@Controller
public class PermissionController extends WebController {

    @Resource
    private PermissionService permissionService;

    @Resource
    private CommonService commonService;

    /**
     * 权限点列表页
     */
    @RequestMapping(value = "/menu/permissions", method = RequestMethod.GET)
    public String list(@RequestParam(value = "menu_id", required = false) Integer menuId,
                       @RequestParam(value = "menu_page", required = false) Integer menuPage,
                       @RequestParam(value = "pid", required = false) Integer pid,
                       Pagination pagination, Model model) {
        model.addAttribute("menuId", menuId);
        model.addAttribute("pagination", pagination);
        model.addAttribute("pid", pid);
        model.addAttribute("menuPage", menuPage);
       // model.addAttribute("menu", menuService.get(menuId));
        return "menu/permission/list";
    }

    /**
     * 权限点列表页
     */
    @RequestMapping(value = "/menu/permissions", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("page", permissionService.list(pagination, searchParams));
        return "menu/permission/nested";
    }

    /**
     * 创建权限点
     */
    @RequestMapping(value = "/menu/permission", method = RequestMethod.GET)
    public String add() {
        return "menu/permission/edit";
    }

    /**
     * 编辑权限点
     */
    @RequestMapping(value = "/menu/permission/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("permission", permissionService.get(id));
        return "menu/permission/edit";
    }

    /**
     * 保存权限点
     */
    @ResponseBody
    @RequestMapping(value = "/menu/permission/save", method = RequestMethod.POST)
    public JsonMap save(@RequestParam("permission") String permission , @RequestParam("name") String name,
                        @RequestParam("menuId") Integer menuId, @RequestParam(required = false) Integer id) {
        JsonMap ret;
        Permission per = new Permission();
        per.setPermission(permission);
        Menu menu = new Menu();
        menu.setId(menuId);
        per.setMenu(menu);
        per.setName(name);
        per.setId(id);
        permissionService.save(per);
        ret = new JsonMap(0, "保存成功");
        return ret;
    }
//    @ResponseBody
//    @RequestMapping(value = "/savePermission",method = RequestMethod.POST)
//    public JsonMap save(@RequestParam("name") String name,@RequestParam("permission")String permission){
//        Date now=commonService.getCurrentTime();
//        Permission permission1=new Permission();
//        permission1.setCreateTime(now);
//        permission1.setName(name);
//        permission1.setPermission(permission);
//        permissionService.save(permission1);
//        return new JsonMap(0,"保存成功");
//    }

    /**
     * 删除权限点
     */
    @ResponseBody
    @RequestMapping(value = "/menu/permission/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        permissionService.delete(ids);
        return new JsonMap(0, "删除成功");
    }

}
