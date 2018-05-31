package com.wangguang.controller.game;


import com.wangguang.common.vo.Pagination;
import com.wangguang.controller.common.WebController;
import com.wangguang.model.entity.Agent;
import com.wangguang.service.DollLogService;
import com.wangguang.web.JsonMap;
import com.wangguang.web.Servlets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@RequestMapping("/doll/log")
@Controller
public class DollLogController extends WebController {

    @Resource
    private DollLogService dollLogService;



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model){
        model.addAttribute("pagination", pagination);
        model.addAttribute("isAgent",isAgent());
        return "doll/log/list";
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
        Page page = dollLogService.list(pagination, searchParams, new Sort(Sort.Direction.DESC,"id"));
        model.addAttribute("page", page);
        model.addAttribute("isAgent",isAgent());
        return "doll/log/nested";
    }


    @RequestMapping(value = "/chartList", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap chartList(@RequestParam(value = "searchType",required = true) Integer searchType,
                             @RequestParam(value = "agentId",required = true) Integer agentId,
                             @RequestParam(value = "startDate",required = false) Date startDate,
                             @RequestParam(value = "endDate",required = false) Date endDate, Model model){

        return  dollLogService.catchTotal(searchType,agentId,startDate,endDate);
    }




}
