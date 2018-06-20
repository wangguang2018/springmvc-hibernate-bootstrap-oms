package com.wangguang.controller.modal;

import com.google.common.collect.Lists;
import com.wangguang.common.vo.Pagination;
import com.wangguang.controller.common.WebController;
import com.wangguang.dao.MachineDao;
import com.wangguang.dao.MachineSetDao;
import com.wangguang.model.dto.EnCodeLevelListDto;
import com.wangguang.model.entity.Agent;
import com.wangguang.model.entity.Machine;
import com.wangguang.model.entity.MachineSet;
import com.wangguang.model.entity.MachineUpdateRecord;
import com.wangguang.model.enums.EnumEncodeLevel;
import com.wangguang.service.MachineService;
import com.wangguang.service.MachineUpdateRecordService;
import com.wangguang.service.ProductService;
import com.wangguang.services.ExceptionCode;
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
 * 娃娃机
 */
@RequestMapping("/machine")
@Controller
public class MachineController extends WebController {

    @Resource
    private MachineService machineService;


    @Resource
    private MachineDao machineDao;

    @Resource
    private MachineUpdateRecordService machineUpdateRecordService;

    @Resource
    private ProductService productService;

    @Resource
    private MachineSetDao machineSetDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        model.addAttribute("isAgent", isAgent());
        model.addAttribute("encodeLevels", EnumEncodeLevel.values());
        return "machine/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag", 1);
        if (isAgent()) {
            Agent agent = getLoginAgent();
            searchParams.put("EQ_agentId", agent.getId());
        }
        model.addAttribute("isAgent", isAgent());
        Page page = machineService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "sort"));
        model.addAttribute("page", page);
        return "machine/nested";
    }


    @RequestMapping(value = "/set/list", method = RequestMethod.GET)
    public String msetlist(Pagination pagination, Model model) {
        model.addAttribute("pagination", pagination);
        model.addAttribute("isAgent", isAgent());
        model.addAttribute("encodeLevels", EnumEncodeLevel.values());
        return "machine/set/list";
    }

    @RequestMapping(value = "/set/list", method = RequestMethod.POST)
    public String msetlist(Pagination pagination, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        searchParams.put("EQ_flag", 1);
        if (isAgent()) {
            Agent agent = getLoginAgent();
            searchParams.put("EQ_agentId", agent.getId());
        }
        model.addAttribute("isAgent", isAgent());
        Page page = machineService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "sort"));
        model.addAttribute("page", page);
        return "machine/set/nested";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String save(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("productList", productService.findAllProducts());
        model.addAttribute("machine", machineService.get(id));
        return "machine/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String save(Model model) {
        model.addAttribute("productList", productService.findAllProducts());
        model.addAttribute("machine", new Machine());
        return "machine/edit";
    }

    @RequestMapping(value = "/levelModal", method = RequestMethod.POST)
    public String levelModal(@RequestParam("id[]") Integer[] ids, Model model) {
        List<Machine> machines = machineDao.findByIds(ids);
        model.addAttribute("machines", machines);
        model.addAttribute("encodeLevels", EnumEncodeLevel.values());
        return "machine/encodeLevelModal";
    }

    @RequestMapping(value = "/saveLevel", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap saveLevel(EnCodeLevelListDto listDto) {
        return machineService.setStreams(listDto);
    }

    @RequestMapping(value = "/onlineUpdate", method = RequestMethod.GET)
    public String onlineUpdate(MachineUpdateRecord record, Model model) {
        if (isAgent()) {
            return "error/500";
        }
        machineService.findAll();
        List<Machine> machineList = machineService.findAll();
        model.addAttribute("machineList", machineList);
        return "machine/online-update";
    }

    @RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap saveUpdate(MachineUpdateRecord record, @RequestParam("machineIds") String machineIds) {
        machineUpdateRecordService.save(record, machineIds);
        return new JsonMap(0, "保存成功");
    }


    @RequestMapping(value = "/updateSort", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap saveUpdate(@RequestParam("id") int id, @RequestParam("sort") int sort) {
        machineService.updateSort(id, sort);
        return new JsonMap(0, "更新成功");
    }

    /*@RequestMapping(value = "/updateType", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap updateType(@RequestParam("id") int id) {
        machineService.updateType(id);
        return new JsonMap(0, "更新成功");
    }*/


    @RequestMapping(value = "/checkMark", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap checkMark(@RequestParam("id") int id, @RequestParam("mark") String mark) {
        return machineService.checkMark(id, mark);
    }

    @RequestMapping(value = "/updateMark", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap updateMark(@RequestParam("id") int id, @RequestParam("mark") String mark) {
        return machineService.updateMark(id, mark);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap save(Machine machine, @RequestParam("liveRoom1Code") String liveRoom1, @RequestParam("liveRoom2Code") String liveRoom2) {
        machineService.saveMachine(machine, liveRoom1, liveRoom2);
        return new JsonMap(0, "保存成功");
    }


    @RequestMapping(value = "/machineSet", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap getMachineSet(@RequestParam("machineId") Integer machineId) {
        MachineSet machineSet = machineSetDao.findBymachineId(machineId);
        MachineSet machSet = new MachineSet();
        if (machineSet != null) {
            machSet.setGameMode(machineSet.getGameMode());
            machSet.setProbability(machineSet.getProbability());
            machSet.setStrongVoltage(machineSet.getStrongVoltage());
            machSet.setSmallVoltage(machineSet.getSmallVoltage());
            machSet.setChangeTime(machineSet.getChangeTime());
            machSet.setStatus(machineSet.getStatus());
            machSet.setChangeWeak(machineSet.getChangeWeak());
        }
        JsonMap jsonMap = new JsonMap(0, "保存成功");
        jsonMap.put("machine", machSet);
        return jsonMap;
    }


    @RequestMapping(value = "/choosePro", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap choosePro(@RequestParam("id") Integer id, @RequestParam("productId") Integer productId) {
        machineService.choosePro(id, productId);
        return new JsonMap(0, "保存成功");
    }

    /**
     * 设置机器参数
     *
     * @param machineSet
     * @return
     */
    @RequestMapping(value = "/setting", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap setting(MachineSet machineSet,@RequestParam(value = "ids[]",required = false) Integer[] ids) {
        machineService.setting(machineSet,ids);
        return new JsonMap();
    }


    /**
     * 设置机器推流参数
     *
     * @return
     */
    @RequestMapping(value = "/setParam", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap setParam(MachineSet dto, HttpServletRequest request) {
        int tabType = Integer.parseInt(request.getParameter("tabType"));

        if (tabType == 1) {//摄像头设置

            int encodeLevelSwitch = 1;
            //分辨率选的不设置
            if (dto.getEncodeLevel() == null || dto.getEncodeLevel() < 0) {
                encodeLevelSwitch = 0;
            }
            List<Machine> list = Lists.newArrayList();
            if (dto.getMachineId() == null || dto.getMachineId() <= 0) {
                Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
                searchParams.put("EQ_flag", 1);
                if (isAgent()) {
                    Agent agent = getLoginAgent();
                    searchParams.put("EQ_agentId", agent.getId());
                }
                list = machineService.list(searchParams, new Sort(Sort.Direction.DESC, "sort"));
            }
            return machineService.setParam(dto, (byte) encodeLevelSwitch, list);
        } else if (tabType == 2) {//推流设置
            return machineService.setMachineStream(dto.getMachineId(), dto.getTestStreamSwitch(), dto.getRotationAngle());
        } else {
            return new JsonMap(ExceptionCode.PARAM_EXCEPTION);
        }


    }


    /**
     * 设置机器
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/settop", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap setTop(@RequestParam("id[]") Integer[] ids, @RequestParam("type") Integer type) {
        machineService.setTop(ids, type);
        return new JsonMap();
    }


    @RequestMapping(value = "/onlineOperate", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap onlineOperate(@RequestParam("id[]") Integer[] ids, @RequestParam("val") int val) {
        machineService.updateFixStatus(ids, val);
        return new JsonMap();
    }


    /**
     * 设置机器代理商
     *
     * @return
     */
    @RequestMapping(value = "/chooseAgent", method = RequestMethod.POST)
    @ResponseBody
    public JsonMap setAgent(@RequestParam("agentId") Integer agentId, @RequestParam("id") Integer machineId) {
        machineService.setMachineAgent(agentId, machineId);
        return new JsonMap();
    }


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
        machineService.deleteIds(ids);
        return new JsonMap(0, "删除成功");
    }

    @RequestMapping(value = "/paramPage", method = RequestMethod.GET)
    public String paramPage(@RequestParam("id") Integer id, Model model) {
        JsonMap map = machineService.addParamAttribute(model, id);
        if ((Integer) map.get("code") != 0) {
            throw new RuntimeException("后台数据错误");
        }
        return "machine/machineParamModal";
    }

    @RequestMapping(value = "/set/{type}", method = RequestMethod.GET)
    public JsonMap setMachine(@PathVariable("type") int type, Model model,@RequestParam("id") int machineId){
        return machineService.setMachine(type,machineId);
    }

}
