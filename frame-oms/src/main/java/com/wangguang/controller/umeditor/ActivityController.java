package com.wangguang.controller.umeditor;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wangguang.common.vo.Pagination;
import com.wangguang.controller.common.WebController;
import com.wangguang.dao.AgentDao;
import com.wangguang.entity.Activity;
import com.wangguang.model.entity.Agent;
import com.wangguang.service.ActivityService;
import com.wangguang.services.CommonService;
import com.wangguang.web.JsonMap;
import com.wangguang.web.Servlets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/umeditor/activity")
@Controller
public class ActivityController extends WebController {

    @Resource
    private ActivityService activityService;

    @Resource
    private AgentDao agentDao;

    @Resource
    private CommonService commonService;

    private static String orderName = "createTime";
    private static Sort.Direction orderDirection = Sort.Direction.DESC;

    /**
     * 代理商列表
     */
    @RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        model.addAttribute("isAgent",isAgent());
        Date currentDate = commonService.getCurrentDate();
        model.addAttribute("defaultEndDate", currentDate);
        return "activity/list";
    }

    /**
     * 代理商列表
     */
    @RequestMapping(value = {"", "/list"}, method = RequestMethod.POST)
    public String agents(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        Page page = activityService.list(pagination, searchParams, new Sort(orderDirection, orderName));
        model.addAttribute("page", page);
        model.addAttribute("isAgent",isAgent());
        return "activity/nested";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String save(@PathVariable("id") Integer id, Model model){
        model.addAttribute("activity",activityService.get(id));
        return "activity/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String save(Model model,HttpServletRequest request){
        return "activity/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap save(Activity activity){
        activity.setAgentId(getLoginAgent().getId());
        return  activityService.saveActivity(activity);

    }



    /**
     * 获取代理商
     */
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Map<String, Object>> getAgents() {
        /*List<Agent> agents = activityService.findAll();
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> fir = Maps.newHashMap();
        fir.put("id", "");
        fir.put("text", "全部");
        list.add(fir);
        for (Agent a : agents) {
            Map<String, Object> obj = Maps.newHashMap();
            obj.put("id", a.getId());
            obj.put("text", a.getName());
            list.add(obj);
        }*/
        return null;
    }

    /**
     * 获取自己的代理商
     */
    @ResponseBody
    @RequestMapping(value = "/getMyList", method = RequestMethod.GET)
    public List<Map<String, Object>> getMyAgents() {
        List<Agent> agents = Lists.newArrayList();
        if(isAgent()){
            Agent agent = getLoginAgent();
            agents = agentDao.getSubsAndSelf(agent.getId());
        }else{
            //agents = activityService.findAll();
        }
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> fir = Maps.newHashMap();
        fir.put("id", "");
        fir.put("text", "所有代理商");
        list.add(fir);
        for (Agent a : agents) {
            Map<String, Object> obj = Maps.newHashMap();
            obj.put("id", a.getId());
            obj.put("text", a.getName());
            list.add(obj);
        }
        return list;
    }

}
