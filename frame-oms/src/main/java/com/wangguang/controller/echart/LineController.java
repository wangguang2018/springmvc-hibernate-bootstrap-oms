package com.wangguang.controller.echart;


import com.wangguang.common.vo.Pagination;
import com.wangguang.controller.common.WebController;
import com.wangguang.dao.AgentDao;
import com.wangguang.model.enums.EnumStatisticsSearchType;
import com.wangguang.model.sys.User;
import com.wangguang.service.DollLogService;
import com.wangguang.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/echart/line")
public class LineController extends WebController {

    @Resource
    private StatisticsService statisticsService;

    @Resource
    private DollLogService dollLogService;


   /* @Resource
    private AgentService agentService;

    @Resource
    private RedisService redisService;*/

    @Resource
    private AgentDao agentDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Pagination pagination,Model model) {
        User user = getLoginUser();
        model.addAttribute("user",user);
        Integer agentId = null;
        if(isAgent()){
            agentId = getLoginAgent().getId();
            model.addAttribute("agents",agentDao.getSubsAndSelf(agentId));
        }else{
            model.addAttribute("agents",agentDao.findAll());
        }
        model.addAttribute("isAgent",isAgent());
        model.addAttribute("todayMember",statisticsService.todayNewMember(agentId));
        model.addAttribute("totalMember",statisticsService.totalMember(agentId));
        model.addAttribute("totalMachine",statisticsService.totalMachine(agentId));
        model.addAttribute("totalCharge",statisticsService.totalChargeMoney(agentId));
        model.addAttribute("chargeList",statisticsService.monthlyCharge(agentId));
        model.addAttribute("monthCharge",statisticsService.monthCharge(agentId));
        model.addAttribute("yearCharge",statisticsService.yearCharge(agentId));
        model.addAttribute("searchTypes", EnumStatisticsSearchType.values());
        model.addAttribute("pagination", pagination);

        return "main";
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public String list(Pagination pagination, Model model,
                       @RequestParam(value = "searchType",required = false) Integer searchType,
                       @RequestParam(value = "agentId",required = true) Integer agentId,
                       @RequestParam(value = "startDate",required = false) Date startDate,
                       @RequestParam(value = "endDate",required = false) Date endDate){

        model.addAttribute("page",dollLogService.page(searchType,agentId,startDate,endDate,pagination.getPage()));

        return "statistics/nested";
    }


    /*@RequestMapping(value = "/agent", method = RequestMethod.POST)
    public String list(Pagination pagination, Model model){
        Map<String,Object> searchParams= Maps.newHashMap();
        pagination.setPageSize(5);
        searchParams.put("EQ_pid",0);
        searchParams.put("EQ_flag",1);
        Page<Agent> page = agentService.list(pagination, searchParams, new Sort(Sort.Direction.DESC, "createTime"));
        List<StatisticDto> dtos = Lists.newArrayList();
        for(Agent agent : page.getContent()){
            StatisticDto sta = new StatisticDto();
            int agentId = agent.getId();
            sta.setTodayMember(statisticsService.todayNewMember(agentId));
            sta.setTotalMember(statisticsService.totalMember(agentId));
            sta.setTotalMachine(statisticsService.totalMachine(agentId));
            sta.setTotalCharge(statisticsService.totalChargeMoney(agentId));
            sta.setMonthCharge(statisticsService.agentMonthCharge(agentId));
            sta.setAgentName(agent.getName());
            dtos.add(sta);

        }
        Page<StatisticDto> stas = new Page<StatisticDto>() {
            @Override
            public int getTotalPages() {
                return page.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return page.getTotalElements();
            }

            @Override
            public int getNumber() {
                return page.getNumber();
            }

            @Override
            public int getSize() {
                return page.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return page.getNumberOfElements();
            }

            @Override
            public List<StatisticDto> getContent() {
                return dtos;
            }

            @Override
            public boolean hasContent() {
                return page.hasContent();
            }

            @Override
            public Sort getSort() {
                return page.getSort();
            }

            @Override
            public boolean isFirst() {
                return page.isFirst();
            }

            @Override
            public boolean isLast() {
                return page.isLast();
            }

            @Override
            public boolean hasNext() {
                return page.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return page.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return page.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return page.previousPageable();
            }

            @Override
            public Iterator<StatisticDto> iterator() {
                return null;
            }
        };

        model.addAttribute("page", stas);

        return "statistics/agentNested";
    }*/



}
