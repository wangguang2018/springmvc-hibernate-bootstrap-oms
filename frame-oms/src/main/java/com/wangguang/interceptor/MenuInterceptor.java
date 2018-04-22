package com.wangguang.interceptor;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wangguang.common.bo.Constant;
import com.wangguang.model.sys.Menu;
import com.wangguang.service.AccountService;
import com.wangguang.service.MenuService;
import com.wangguang.web.CommonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class MenuInterceptor implements HandlerInterceptor {

    @Resource
    private MenuService menuService;

    @Resource
    private AccountService accountService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
// 剔除异步请求
        if ("GET".equalsIgnoreCase(request.getMethod()) && !CommonUtils.isAjaxRequest(request) && modelAndView != null) {

            HttpSession session = request.getSession(true);

            // 查询所有菜单 和 当前访问地址
            String currentAccount = accountService.getCurrentAccount();
            List<Menu> menuList;
            List<Menu> rootMenuList;
            List<Menu> childMenuList;
            //如果是超级管理员时
            if (accountService.isSupervisor(currentAccount)) {
                menuList = getAdminMenus();
            } else {
                menuList = menuService.findMenuList(accountService.getCurrentUser().getId(), Constant.Status.Enable);
            }
            rootMenuList = getRootMenu(menuList);
            childMenuList = getChildMenu(menuList);

            String currentURI = request.getRequestURI();

            // 一级菜单编号 和 子菜单编号
            Integer activeRootMenuId = 0;
            if (StringUtils.isNoneBlank(request.getParameter("menu_id"))) {
                activeRootMenuId = Integer.parseInt(request.getParameter("menu_id"));
                session.setAttribute("menuId", activeRootMenuId);
            } else if (session.getAttribute("menuId") != null) {
                activeRootMenuId = (Integer) session.getAttribute("menuId");
            }
            Integer activeMenuId = 0;
            if (StringUtils.isNoneBlank(request.getParameter("child_menu_id"))) {
                activeMenuId = Integer.parseInt(request.getParameter("child_menu_id"));
                session.setAttribute("childMenuId", activeMenuId);
            } else if (session.getAttribute("childMenuId") != null) {
                activeMenuId = (Integer) session.getAttribute("childMenuId");
            }

            modelAndView.addObject("menus", rootMenuList);
            modelAndView.addObject("childMenus", childMenuList);
            modelAndView.addObject("activeRootMenuId", activeRootMenuId);
            modelAndView.addObject("activeMenuId", activeMenuId);


            /*if (logger.isDebugEnabled()) {
                logger.debug("activeRootMenuId = " + activeRootMenuId);
                logger.debug("activeMenuId = " + activeMenuId);
            }*/
        }
    }

    private List<Menu> getAdminMenus() {
        List<Menu> rootMenuList = Lists.newArrayList();
        Menu menu = new Menu(1, "系统管理", "", "fa fa-cogs", null);
        Set<Menu> childMenuList = Sets.newHashSet();
        childMenuList.add(new Menu(2, "用户管理", "users", "fa fa-cogs", menu));
        childMenuList.add(new Menu(3, "菜单管理", "menus", "", menu));
        childMenuList.add(new Menu(4, "角色管理", "roles", "fs", menu));
        menu.setChildMenu(childMenuList);
        rootMenuList.add(menu);
        rootMenuList.addAll(childMenuList);
        return rootMenuList;
    }


    /**
     * 遍历获取所有父菜单
     *
     * @param menuList 菜单
     * @return 所有父菜单
     */
    private List<Menu> getRootMenu(List<Menu> menuList) {
        List<Menu> rootMenuList = new ArrayList<Menu>();

        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                // 判断有无父菜单
                if (menu.getParentMenu() == null) {
                    rootMenuList.add(menu);
                }
            }
        }
        //添加对父菜单的排序功能
        Collections.sort(rootMenuList, new Comparator<Menu>(){
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getSort().compareTo(o2.getSort());
            }
        });
        return rootMenuList;
    }

    /**
     * 遍历获取所有子菜单
     *
     * @param menuList 菜单
     * @return 所有父菜单
     */
    private List<Menu> getChildMenu(List<Menu> menuList) {
        List<Menu> childMenuList = new ArrayList<Menu>();

        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                // 判断有无父菜单
                if (menu.getParentMenu() != null) {
                    childMenuList.add(menu);
                }
            }
        }
        //添加对子菜单的排序功能
        Collections.sort(childMenuList, new Comparator<Menu>(){
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getSort().compareTo(o2.getSort());
            }
        });
        return childMenuList;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
