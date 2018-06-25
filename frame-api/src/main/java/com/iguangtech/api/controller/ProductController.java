package com.iguangtech.controller;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wangguang.common.vo.Pagination;
import com.wangguang.controller.common.WebController;
import com.wangguang.model.entity.Agent;
import com.wangguang.model.entity.Machine;
import com.wangguang.model.entity.Product;
import com.wangguang.model.enums.EnumProductType;
import com.wangguang.service.MachineService;
import com.wangguang.service.ProductService;
import com.wangguang.services.CommonService;
import com.wangguang.web.JsonMap;
import com.wangguang.web.Servlets;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * 娃娃产品
 */
@Controller
@RequestMapping("/product")
public class ProductController extends WebController {

    @Resource
    private CommonService commonService;

    @Resource
    private ProductService productService;




    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        searchParams.put("EQ_type", EnumProductType.doll.value);
        if(isAgent()){
            Agent agent = getLoginAgent();
            searchParams.put("EQ_agentId",agent.getId());
        }
        Page page = productService.list(pagination, searchParams, new Sort(Sort.Direction.DESC,"id"));
        model.addAttribute("page", page);
        return "product/nested";
    }


}
