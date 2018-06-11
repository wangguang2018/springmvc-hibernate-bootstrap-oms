package com.wangguang.controller.shiroPermission;


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
 * 娃娃产品
 */
@Controller
@RequestMapping("/product")
public class ProductController extends WebController {

    @Resource
    private CommonService commonService;

    @Resource
    private ProductService productService;

    @Resource
    private MachineService machineService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model){
        model.addAttribute("pagination", pagination);
        model.addAttribute("isAgent",isAgent());
        return "product/list";
    }

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


    @RequestMapping(value = "/exchangeProduct/list", method = RequestMethod.GET)
    public String exchangeProductList(Pagination pagination, Model model){
        model.addAttribute("pagination", pagination);
        model.addAttribute("isAgent",isAgent());
        return "product/exchangeProduct/list";
    }

    @RequestMapping(value = "/exchangeProduct/list", method = RequestMethod.POST)
    public String exchangeProductList(Pagination pagination, HttpServletRequest request, Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        searchParams.put("EQ_type",EnumProductType.exchangeProduct.value);
        if(isAgent()){
            Agent agent = getLoginAgent();
            searchParams.put("EQ_agentId",agent.getId());
        }
        Page page = productService.list(pagination, searchParams, new Sort(Sort.Direction.DESC,"id"));
        model.addAttribute("page", page);
        return "product/exchangeProduct/nested";
    }


    @RequestMapping(value = "/exchangeBigDoll/list", method = RequestMethod.GET)
    public String exchangeBigDollList(Pagination pagination, Model model){
        model.addAttribute("pagination", pagination);
        model.addAttribute("isAgent",isAgent());
        return "product/exchangeBigDoll/list";
    }

    @RequestMapping(value = "/exchangeBigDoll/list", method = RequestMethod.POST)
    public String exchangeBigDollList(Pagination pagination, HttpServletRequest request, Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        searchParams.put("EQ_type",EnumProductType.exchangeBigDoll.value);
        if(isAgent()){
            Agent agent = getLoginAgent();
            searchParams.put("EQ_agentId",agent.getId());
        }
        Page page = productService.list(pagination, searchParams, new Sort(Sort.Direction.DESC,"id"));
        model.addAttribute("page", page);
        return "product/exchangeBigDoll/nested";
    }

    /**
     * 获取ieb
     */
    @ResponseBody
    @RequestMapping(value = "/getList/{sn}", method = RequestMethod.GET)
    public List<Map<String, Object>> getProvince(@PathVariable("sn") String machineSn) {
        List<Product> products = new ArrayList<>();
        if(isAgent())
            products = productService.findProductsByAgentIdAndType(getLoginAgent().getId(),EnumProductType.doll.value);
        else {
            Machine machine = machineService.findBySn(machineSn);
            if(machine != null && machine.getAgentId() != null)
                products = productService.findProductsByAgentIdAndType(machine.getAgentId(),EnumProductType.doll.value);
        }
        List<Map<String, Object>> list = Lists.newArrayList();
        /*Map<String, Object> fir = Maps.newHashMap();
        fir.put("id", -1);
        fir.put("text", "请选择");
        list.add(fir);*/
        for (Product a : products) {
            Map<String, Object> obj = Maps.newHashMap();
            obj.put("id", a.getId());
            obj.put("text", a.getName());
            list.add(obj);
        }
        return list;
    }

    /**
     * select2
     */
    @ResponseBody
    @RequestMapping(value = "/exchange/list", method = RequestMethod.GET)
    public List<Map<String, Object>> exchangeProducts() {
        List<Product> products = Lists.newArrayList();
        products = productService.findProductsByAgentIdAndType(getLoginAgent().getId(),EnumProductType.exchangeProduct.value);
        List<Map<String, Object>> list = Lists.newArrayList();
        /*Map<String, Object> fir = Maps.newHashMap();
        fir.put("id", -1);
        fir.put("text", "请选择");
        list.add(fir);*/
        for (Product a : products) {
            Map<String, Object> obj = Maps.newHashMap();
            obj.put("id", a.getId());
            obj.put("text", a.getName());
            list.add(obj);
        }
        return list;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model){
        Product product = productService.get(id);
        model.addAttribute("product",product);
        if(product.getType() == EnumProductType.doll.value){
            return "product/edit";
        }else if(product.getType() == EnumProductType.exchangeProduct.value){
            return "product/exchangeProduct/edit";
        }else if(product.getType() == EnumProductType.exchangeBigDoll.value){
            return "product/exchangeBigDoll/edit";
        }else{
            return "error/404";
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMap myInfo(@PathVariable("id") Integer id){
        Product product = productService.get(id);
        JsonMap map = new JsonMap();
        map.put("info",product);
        return  map;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,@RequestParam("type") byte type){
        Product product = new Product();

        if(type == EnumProductType.doll.value){
            product.setType(EnumProductType.doll.value);
            model.addAttribute("product",product);
            return "product/edit";
        }else if(type == EnumProductType.exchangeProduct.value){
            product.setType(EnumProductType.exchangeProduct.value);
            model.addAttribute("product",product);
            return "product/exchangeProduct/edit";
        }else if(type == EnumProductType.exchangeBigDoll.value){
            product.setType(EnumProductType.exchangeBigDoll.value);
            model.addAttribute("product",product);
            return "product/exchangeBigDoll/edit";
        }else{
            return "error/404";
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap save(Product product){
        if(product.getPrice()==null){
            product.setPrice(BigDecimal.ZERO);
        }
        if(product.getId() == null)
            product.setCreateTime(commonService.getCurrentTime());
        else
            product.setUpdateTime(commonService.getCurrentTime());
        if(isAgent())
            product.setAgentId(getLoginAgent().getId());
        productService.save(product);
        return new JsonMap(0,"保存成功");
    }

    /**
     * 删除商品
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonMap delete(@RequestParam("id[]") Integer[] ids){
        productService.deleteProduct(ids);
        return new JsonMap(0,"删除成功");
    }

}
