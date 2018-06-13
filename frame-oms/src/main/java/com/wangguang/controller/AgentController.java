package com.wangguang.controller;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wangguang.common.vo.Pagination;
import com.wangguang.controller.common.WebController;
import com.wangguang.dao.AgentDao;
import com.wangguang.model.entity.Agent;
import com.wangguang.model.enums.EnumAgentType;
import com.wangguang.service.AgentService;
import com.wangguang.web.JsonMap;
import com.wangguang.web.Servlets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/agent")
@Controller
public class AgentController extends WebController {

    @Resource
    private AgentService agentService;

    @Resource
    private AgentDao agentDao;

    private static String orderName = "createTime";
    private static Sort.Direction orderDirection = Sort.Direction.DESC;

    /**
     * 代理商列表
     */
    @RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        return "agent/list";
    }

    /**
     * 代理商列表
     */
    @RequestMapping(value = {"", "/list"}, method = RequestMethod.POST)
    public String agents(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        Page page = agentService.list(pagination, searchParams, new Sort(orderDirection, orderName));
        model.addAttribute("page", page);
        model.addAttribute("isAgent",isAgent());
        return "agent/nested";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String save(@PathVariable("id") Integer id, Model model){
        Agent agent = agentService.get(id);
        model.addAttribute("agent",agent);
        model.addAttribute("account",agent.getUser().getAccount());
        return "agent/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String save(Model model,HttpServletRequest request){
        model.addAttribute("types", EnumAgentType.values());
        int pid = 0;
        try{
            pid = Integer.valueOf(request.getParameter("pid"));
        }catch (Exception exception){

        }
        if(pid != 0){
            Agent agent = agentService.get(pid);
            agent.setId(null);
            model.addAttribute("agent",agentService.get(pid));
        }
        model.addAttribute("pid",pid);
        return "agent/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap save(Agent agent, @RequestParam(value = "account") String account){
        return agentService.saveAgent(agent,account);

    }

    /**
     * 禁用
     * @param ids
     * @return
     */
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap disable(@RequestParam("id[]") Integer[] ids){
        agentService.disable(ids);
        return new JsonMap(0,"禁用成功");
    }

    /**
     * 启用
     * @param ids
     * @return
     */
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap enable(@RequestParam("id[]") Integer[] ids){
        agentService.enable(ids);
        return new JsonMap(0,"启用成功");
    }


    /**
     * 获取代理商
     */
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Map<String, Object>> getAgents() {
        List<Agent> agents = agentService.findAll();
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
        }
        return list;
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
            agents = agentService.findAll();
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

    /**
     * 获取自己的代理商
     */
    @ResponseBody
    @RequestMapping(value = "/getMyListAgents", method = RequestMethod.GET)
    public List<Map<String, Object>> getMyListAgents() {
        List<Agent> agents = Lists.newArrayList();
        if(isAgent()){
            Agent agent = getLoginAgent();
            agents = agentDao.getSubsAndSelf(agent.getId());
        }else{
            agents = agentService.findAll();
        }
        List<Map<String, Object>> list = Lists.newArrayList();
       /* Map<String, Object> fir = Maps.newHashMap();
        fir.put("id", "");
        fir.put("text", "所有代理商");
        list.add(fir);*/
        for (Agent a : agents) {
            Map<String, Object> obj = Maps.newHashMap();
            obj.put("id", a.getId());
            obj.put("text", a.getName());
            list.add(obj);
        }
        return list;
    }


}
