package com.wangguang.controller.tag;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wangguang.common.vo.Pagination;
import com.wangguang.controller.common.WebController;
import com.wangguang.dao.TagCategoryDao;
import com.wangguang.dao.TagMachineDao;
import com.wangguang.model.entity.Agent;
import com.wangguang.model.entity.Machine;
import com.wangguang.model.entity.Tag;
import com.wangguang.model.entity.TagCategory;
import com.wangguang.service.MachineService;
import com.wangguang.service.TagService;
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
import java.util.List;
import java.util.Map;

/**
 * 娃娃产品
 */

@RequestMapping("/tag")
@Controller
public class TagController extends WebController {

    @Resource
    private CommonService commonService;

    @Resource
    private TagService tagService;

    @Resource
    private TagMachineDao tagMachineDao;

    @Resource
    private MachineService machineService;

    @Resource
    private TagCategoryDao tagCategoryDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model){
        model.addAttribute("pagination", pagination);
        model.addAttribute("isAgent",isAgent());
        return "tag/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag",1);
        if(isAgent()){
            Agent agent = getLoginAgent();
            searchParams.put("EQ_agentId",agent.getId());
        }
        Page page = tagService.list(pagination, searchParams, new Sort(Sort.Direction.DESC,"id"));
        model.addAttribute("page", page);
        model.addAttribute("isAgent",isAgent());
        return "tag/nested";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String save(@PathVariable("id") Integer id,Model model){
        Tag tag = tagService.get(id);
        List<Machine> machineList = machineService.findAllByAgentId(tag.getAgentId());
        List<String> machineSn = tagMachineDao.getMachineSn(id);
        model.addAttribute("machineSn", String.join(",",machineSn));
        model.addAttribute("machineList",machineList);
        model.addAttribute("tag",tag);
        return "tag/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String save(Model model){
        List<Machine> machineList = machineService.findAllByAgentId(getLoginAgent().getId());
        model.addAttribute("machineList",machineList);
        model.addAttribute("tag",new Tag());
        return "tag/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap save(Tag tag, @RequestParam("machineIds") String machineIds){
        if(tag.getId() == null)
            tag.setCreateTime(commonService.getCurrentTime());
        else
            tag.setUpdateTime(commonService.getCurrentTime());
        if(isAgent())
            tag.setAgentId(getLoginAgent().getId());
        tagService.saveTag(tag,machineIds);
        return new JsonMap(0,"保存成功");
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids){
        tagService.deleteIds(ids);
        return new JsonMap(0,"删除成功");
    }


    /**
     * 获取自己的代理商
     */
    @ResponseBody
    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    public List<Map<String, Object>> getMyAgents() {
        List<TagCategory> categories = tagCategoryDao.findAll();
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> fir = Maps.newHashMap();
        fir.put("id", "");
        fir.put("text", "所有标签种类");
        list.add(fir);
        for (TagCategory category : categories) {
            Map<String, Object> obj = Maps.newHashMap();
            obj.put("id", category.getId());
            obj.put("text", category.getName());
            list.add(obj);
        }
        return list;
    }




}
