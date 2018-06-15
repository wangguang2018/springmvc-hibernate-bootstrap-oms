package com.wangguang.controller.uploadAndDownload;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wangguang.common.vo.Pagination;
import com.wangguang.controller.common.WebController;
import com.wangguang.model.entity.Agent;
import com.wangguang.model.entity.Order;
import com.wangguang.model.enums.EnumExpress;
import com.wangguang.service.DollOrderService;
import com.wangguang.web.JsonMap;
import com.wangguang.web.Servlets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 娃娃订单
 */
@RequestMapping("/member/order")
@Controller
public class DollOrderController extends WebController {

    @Resource
    private DollOrderService dollOrderService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model){
        model.addAttribute("pagination", pagination);
        model.addAttribute("isAgent",isAgent());
        return "order/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        if("-1".equals(searchParams.get("EQ_status").toString())){
            searchParams.remove("EQ_status");
        }
        if(isAgent()){
            Agent agent = getLoginAgent();
            searchParams.put("EQ_agentId",agent.getId());
        }
        Page page = dollOrderService.list(pagination, searchParams, new Sort(Sort.Direction.DESC,"id"));
        model.addAttribute("page", page);
        return "order/nested";
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ModelAndView exportReconciliation(HttpServletRequest request) {
        try {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            searchParams.put("EQ_flag",1);
            if("-1".equals(searchParams.get("EQ_status").toString())){
                searchParams.remove("EQ_status");
            }
            if(isAgent()){
                Agent agent = getLoginAgent();
                searchParams.put("EQ_agentId",agent.getId());
            }
            ModelAndView model = getExcelView(request);
            List<Order> orders = dollOrderService.list( searchParams, new Sort(Sort.Direction.DESC, "id"));
            dollOrderService.setProductName(orders);
            model.addObject("result", orders);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "{sn}", method = RequestMethod.GET)
    public String info(@PathVariable("sn") String orderSn,Model model){
        Order order = dollOrderService.findByOrderSn(orderSn);
        model.addAttribute("order", order);
        boolean flag = false;
        List<Map<String,Object>> list = Lists.newArrayList();
        for(EnumExpress express : EnumExpress.values()){
            if(EnumExpress.OTHERS.value==express.getValue()){
                flag = true;
                continue;
            }
            Map<String,Object> map = Maps.newHashMap();
            map.put("key",express.getValue());
            map.put("value",express.getLabel());
            list.add(map);
        }
        if(flag){
            Map<String,Object> map = Maps.newHashMap();
            map.put("key",EnumExpress.OTHERS.getValue());
            map.put("value",EnumExpress.OTHERS.getLabel());
            list.add(map);
        }
        model.addAttribute("expressType", list);
        return "order/info";
    }


    @RequestMapping(value = "/uploadPage", method = RequestMethod.GET)
    public String uploadPage(Model model){
        return "order/uploadExcel";
    }



    @ResponseBody
    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    public JsonMap deliver(@RequestParam("orderSn") String orderSn, @RequestParam("expressNo") String expressNo,
                           @RequestParam(value = "expressName",required = true) String expressName, @RequestParam("type") int type) {
        JsonMap ret;
        dollOrderService.deliver(orderSn,expressNo,type,expressName);
        ret = new JsonMap(0, "发货成功");
        return ret;
    }


    /**
     * 上传excel
     * @param fileData
     * @return
     */
    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap uploadExcel(@RequestParam MultipartFile[] fileData) {
        return dollOrderService.uploadExcel(fileData);
    }

}
