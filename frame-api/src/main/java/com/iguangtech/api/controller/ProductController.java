package com.iguangtech.api.controller;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.iguangtech.api.controller.BaseController;
import com.iguangtech.api.dto.ResponseDTO;
import com.iguangtech.api.service.ProductService;
import com.wangguang.model.entity.Agent;
import com.wangguang.model.entity.Machine;
import com.wangguang.model.entity.Product;
import com.wangguang.model.enums.EnumProductType;
import com.wangguang.services.CommonService;
import javafx.scene.control.Pagination;
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
public class ProductController extends BaseController {



    @Resource
    private ProductService productService;




    /**
     * 充值选项列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @com.iguangtech.interceptor.NeedToken
    public ResponseDTO chargeList(){
        return new ResponseDTO();
    }

}
