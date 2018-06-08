package com.wangguang.service;


import com.google.common.collect.Lists;
import com.wangguang.dao.MachineDao;
import com.wangguang.dao.MachineSetDao;
import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.Machine;
import com.wangguang.model.entity.MachineSet;
import com.wangguang.model.enums.EnumFlag;
import com.wangguang.services.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;

@Service
public class MachineService extends BaseService<Machine, Integer> {

    private MachineDao machineDao;

    @Resource
    private CommonService commonService;

   /* @Resource
    private HxService hxService;*/

    /*@Resource
    private RedisService redisService;*/

    /*@Resource
    private MessagePubService messagePubService;*/

    @Resource
    private MachineSetDao machineSetDao;


    @Resource
    private ProductService productService;

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private RestTemplate restTemplate;


    @Resource
    @Override
    public void setBaseDao(BaseDao<Machine, Integer> baseDao) {
        this.baseDao = baseDao;
        machineDao = (MachineDao) baseDao;
    }

    public void saveMachine(Machine machine, String liveRoom1, String liveRoom2) {
        if (machine.getId() == null) {
            machine.setCreateTime(commonService.getCurrentTime());
            //创建环信聊天室
            /*String chatRoom = hxService.createChatRoom(machine.getSn(), machine.getAgentId());
            machine.setChatRoom(chatRoom);*/
            machine.setMemberCount(0);
        } else {
            machine.setUpdateTime(commonService.getCurrentTime());
            if (StringUtils.isBlank(machine.getChatRoom())) {
                //创建环信聊天室
                /*String chatRoom = hxService.createChatRoom(machine.getSn(), machine.getAgentId());
                machine.setChatRoom(chatRoom);*/
            }
        }

        machineDao.save(machine);
        //随机生成4位直播间ID
        Integer roomId = machine.getId() + 1000;
        machine.setLiveRoomId1(roomId);
        machine.setLiveRoomId2(roomId);
        machineDao.save(machine);


    }

    public List<Machine> findAll() {
        return machineDao.findAll();
    }

    public List<Machine> findAllByAgentId(Integer agentId) {
        return machineDao.findAllByAgentId(agentId);
    }

    /**
     * 设置机器参数
     *
     * @param machineSet
     * @return
     */
    @Transactional
    public void setting(MachineSet machineSet, Integer[] ids) {
        Integer machineId = machineSet.getMachineId();
        Integer[] machineIds = {machineSet.getMachineId()};
        if(machineId==null || machineId<=0){
            machineIds = ids;
        }
        Timestamp current = commonService.getCurrentTime();

        for(int i=0;i < machineIds.length;i++){

            machineId = machineIds[i];
            MachineSet machineSet_ = machineSetDao.findBymachineId(machineId);
            Machine machine = machineDao.findOne(machineId);
            machineSet.setMachineId(machineId);
            if (machineSet_ != null) {
                machineSet.setId(machineSet_.getId());
                machineSet.setUpdateTime(current);
            }
            machineSet.setCreateTime(current);
            machineSet.setStatus(1);//等待处理
            machineSet.setFlag(EnumFlag.Normal.getValue());
            if (machineSet.getGameMode() == null) {
                machineSet.setGameMode(2);
            }
            if (StringUtils.isBlank(machineSet.getStrongVoltage())) {
                machineSet.setStrongVoltage("35.25");
            }
            if (StringUtils.isBlank(machineSet.getSmallVoltage())) {
                machineSet.setSmallVoltage("10.8");
            }
            if (StringUtils.isBlank(machineSet.getChangeTime())) {
                machineSet.setChangeTime("1.1");
            }
            if (machineSet.getProbability() == null) {
                machineSet.setProbability(30);
            }
            machineSetDao.save(machineSet);

            MachineSetDto machineSetDto = new MachineSetDto();

//            machineSetDto.setCmd("machineSet");
//            machineSetDto.setVmcNo(machine.getSn());
//            machineSetDto.setProbability(machineSet.getProbability());
//            machineSetDto.setGameMode(machineSet.getGameMode());
//            machineSetDto.setStrongVoltage(machineSet.getStrongVoltage());
//            machineSetDto.setSmallVoltage(machineSet.getSmallVoltage());
//            machineSetDto.setChangeTime(machineSet.getChangeTime());
//            machineSetDto.setChangeWeak(machineSet.getChangeWeak());
//            redisService.convertAndSend("netty-doll-machine", JsonUtils.encode(machineSetDto));//发送给机器

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, Object> map= new LinkedMultiValueMap<>();
            map.add("gameMode", machineSet.getGameMode());
            map.add("strongVoltage", machineSet.getStrongVoltage());
            map.add("smallVoltage", machineSet.getSmallVoltage());
            map.add("changeTime", machineSet.getChangeTime());
            map.add("probability", machineSet.getProbability());
            map.add("changeWeak", machineSet.getChangeWeak());

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
            sendPostRequest(machine.getSn(), "/machine/" + machine.getSn() + "/setting/grab", request);
        }
    }

    /**
     * 发送Post请求
     *
     * @param machineSn 机器编号
     * @param uri 地址
     * @return 请求结果
     */
    private void sendPostRequest(String machineSn, String uri, HttpEntity request) {
        // 获取机器连接服务器对应ip
        String machineServiceIp = MachineGlobalCache.machineServiceMap.get(machineSn);
        // 发送请求
       /* Result result = restTemplate.postForObject("http://" + machineServiceIp + uri, request, Result.class);
        logger.info("机器【{}】内容：{}", machineSn, JsonUtils.encode(result));*/
    }

    public JsonMap machineIdsCache(List<Machine> list) {
        List<Integer> ids = Lists.newArrayList();
        for (Machine machine : list) {
            ids.add(machine.getId());
        }
        String jsonString = com.alibaba.fastjson.JSON.toJSONString(ids);
        redisService.set("machine-param-set-ids", jsonString);
        return new JsonMap();
    }

    private void send(MachineSet machineSet, Byte encodeLevelSwitch, String machineSn) {
//        MachineVideoDto machineVideoDto = new MachineVideoDto();
//        machineVideoDto.setCmd("setting_video");
//        machineVideoDto.setVmcNo(machineSn);
        //设置分辨率,分辨率选得不是“不设置”
//        if (encodeLevelSwitch == 1) {
//            machineVideoDto.setEncodeLevel(machineSet.getEncodeLevel());
//            machineVideoDto.setWidth(machineSet.getWidth());
//            machineVideoDto.setHeight(machineSet.getHeight());
//        }

//        machineVideoDto.setExposureMode(machineSet.getExposureMode());
//        machineVideoDto.setSlaveExposure(machineSet.getSlaveExposure());
//        machineVideoDto.setMasterExposure(machineSet.getMasterExposure());
//        machineVideoDto.setMasterBrightness(machineSet.getMasterBrightness());
//        machineVideoDto.setSlaveBrightness(machineSet.getSlaveBrightness());
//        machineVideoDto.setMasterSaturability(machineSet.getMasterSaturability());
//        machineVideoDto.setSlaveSaturability(machineSet.getSlaveSaturability());
//        redisService.convertAndSend("netty-doll-machine", JsonUtils.encode(machineVideoDto));//发送给机器

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        if (encodeLevelSwitch == 1) {
            map.add("encodeLevel", converToString(machineSet.getEncodeLevel()));
            map.add("width", converToString(machineSet.getWidth()));
            map.add("height", converToString(machineSet.getHeight()));
        }

        map.add("masterExposure", converToString(machineSet.getMasterExposure()));
        map.add("slaveExposure", converToString(machineSet.getSlaveExposure()));
        map.add("exposureMode", converToString(machineSet.getExposureMode()));
        map.add("masterBrightness", converToString(machineSet.getMasterBrightness()));
        map.add("slaveBrightness", converToString(machineSet.getSlaveBrightness()));
        map.add("masterSaturability", converToString(machineSet.getMasterSaturability()));
        map.add("slaveSaturability", converToString(machineSet.getSlaveSaturability()));


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        sendPostRequest(machineSn, "/machine/" + machineSn + "/setting/camera", request);
    }

    public String converToString(Object object) {
        if (object == null) {
            return null;
        }
        return object.toString();
    }

    private void send(MachineSet dataBaseData, MachineSet machineSet, Byte encodeLevelSwitch, String machineSn) {
//        MachineVideoDto machineVideoDto = new MachineVideoDto();
//        machineVideoDto.setCmd("setting_video");
//        machineVideoDto.setVmcNo(machineSn);
//        //设置分辨率,分辨率选得不是“不设置”
//        if (encodeLevelSwitch == 1) {
//            if (dataBaseData.getEncodeLevel() != null) {
//                if (dataBaseData.getEncodeLevel().intValue() != machineSet.getEncodeLevel().intValue()) {
//                    machineVideoDto.setEncodeLevel(machineSet.getEncodeLevel());
//                }
//            } else {
//                machineVideoDto.setEncodeLevel(machineSet.getEncodeLevel());
//            }
//
//            if (machineSet.getEncodeLevel().intValue() == EnumEncodeLevel.userDefined.value) {//自定义分辨率
//                machineVideoDto.setEncodeLevel(machineSet.getEncodeLevel());
//                machineVideoDto.setWidth(machineSet.getWidth());
//                machineVideoDto.setHeight(machineSet.getHeight());
//            }
//        }
//
//        machineVideoDto.setExposureMode(machineSet.getExposureMode());
//        machineVideoDto.setSlaveExposure(machineSet.getSlaveExposure());
//        machineVideoDto.setMasterExposure(machineSet.getMasterExposure());
//        machineVideoDto.setMasterBrightness(machineSet.getMasterBrightness());
//        machineVideoDto.setSlaveBrightness(machineSet.getSlaveBrightness());
//        machineVideoDto.setMasterSaturability(machineSet.getMasterSaturability());
//        machineVideoDto.setSlaveSaturability(machineSet.getSlaveSaturability());
//        redisService.convertAndSend("netty-doll-machine", JsonUtils.encode(machineVideoDto));//发送给机器


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();


//        MachineVideoDto machineVideoDto = new MachineVideoDto();
//        machineVideoDto.setCmd("setting_video");
//        machineVideoDto.setVmcNo(machineSn);
        //设置分辨率,分辨率选得不是“不设置”
        if (encodeLevelSwitch == 1) {
            if (dataBaseData.getEncodeLevel() != null) {
                if (dataBaseData.getEncodeLevel().intValue() != machineSet.getEncodeLevel().intValue()) {
                    map.add("encodeLevel", converToString(machineSet.getEncodeLevel()));
                }
            } else {
                map.add("encodeLevel", converToString(machineSet.getEncodeLevel()));
            }

            if (machineSet.getEncodeLevel().intValue() == EnumEncodeLevel.userDefined.value) {//自定义分辨率
                map.add("encodeLevel", converToString(machineSet.getEncodeLevel()));
                map.add("width", converToString(machineSet.getWidth()));
                map.add("height", converToString(machineSet.getHeight()));
            }
        }

        map.add("exposureMode", converToString(machineSet.getExposureMode()));
        map.add("masterExposure", converToString(machineSet.getMasterExposure()));
        map.add("slaveExposure", converToString(machineSet.getSlaveExposure()));
        map.add("masterBrightness", converToString(machineSet.getMasterBrightness()));
        map.add("slaveBrightness", converToString(machineSet.getSlaveBrightness()));
        map.add("masterSaturability", converToString(machineSet.getMasterSaturability()));
        map.add("slaveSaturability", converToString(machineSet.getSlaveSaturability()));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        sendPostRequest(machineSn, "/machine/" + machineSn + "/setting/camera", request);
    }


    private MachineSet putDataForAdd(MachineSet machineSet, Timestamp current) {
        MachineSet set = new MachineSet();
        set.setMachineId(machineSet.getMachineId());
        set.setFlag(EnumFlag.Normal.value);
        set.setCreateTime(current);
        set.setUpdateTime(current);
        set.setOpenExposureSetting(machineSet.getOpenExposureSetting());
        set.setExposureMode(machineSet.getExposureMode());
        set.setMasterExposure(machineSet.getMasterExposure());
        set.setSlaveExposure(machineSet.getSlaveExposure());
        set.setMasterBrightness(machineSet.getMasterBrightness());
        set.setSlaveBrightness(machineSet.getSlaveBrightness());
        set.setMasterSaturability(machineSet.getMasterSaturability());
        set.setSlaveSaturability(machineSet.getSlaveSaturability());
        set.setEncodeLevel(machineSet.getEncodeLevel());
        set.setWidth(machineSet.getWidth());
        set.setHeight(machineSet.getHeight());
        return set;
    }

    private MachineSet putDataForEdit(MachineSet set, MachineSet machineSet, Timestamp current) {
        set.setUpdateTime(current);
        set.setOpenExposureSetting(machineSet.getOpenExposureSetting());
        set.setExposureMode(machineSet.getExposureMode());
        set.setMasterExposure(machineSet.getMasterExposure());
        set.setSlaveExposure(machineSet.getSlaveExposure());
        set.setMasterBrightness(machineSet.getMasterBrightness());
        set.setSlaveBrightness(machineSet.getSlaveBrightness());
        set.setMasterSaturability(machineSet.getMasterSaturability());
        set.setSlaveSaturability(machineSet.getSlaveSaturability());
        set.setEncodeLevel(machineSet.getEncodeLevel());
        if (set.getEncodeLevel() != null && machineSet.getEncodeLevel() != null) {
            if (machineSet.getEncodeLevel().intValue() != EnumEncodeLevel.userDefined.value && set.getEncodeLevel().intValue() != machineSet.getEncodeLevel().intValue()) {
                set.setEncodeLevel(machineSet.getEncodeLevel());
                set.setWidth(machineSet.getWidth());
                set.setHeight(machineSet.getHeight());
            }
        }
        if (machineSet.getEncodeLevel() != null && machineSet.getEncodeLevel().intValue() == EnumEncodeLevel.userDefined.value) {
            set.setEncodeLevel(machineSet.getEncodeLevel());
            set.setWidth(machineSet.getWidth());
            set.setHeight(machineSet.getHeight());
        }
        return set;
    }

    private boolean checkMachineVideoDto(MachineSet set, MachineSet machineSet, Byte encodeLevelSwitch) {
        if (set.getOpenExposureSetting() == machineSet.getOpenExposureSetting()
                && set.getExposureMode() == machineSet.getExposureMode()
                && set.getEncodeLevel() == machineSet.getEncodeLevel()
                && set.getMachineId() == machineSet.getMachineId()
                && set.getMasterExposure() == machineSet.getMasterExposure()
                && set.getMasterBrightness() == machineSet.getMasterBrightness()
                && set.getMasterSaturability() == machineSet.getMasterSaturability()
                && set.getSlaveExposure() == machineSet.getSlaveExposure()
                && set.getSlaveBrightness() == machineSet.getSlaveBrightness()
                && set.getSlaveSaturability() == machineSet.getSlaveSaturability()
                && encodeLevelSwitch == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 某个机器的推流设置
     *
     * @param machineId
     * @param testStreamSwitch
     * @param rotationAngle
     * @return
     */
    @Transactional
    public JsonMap setMachineStream(int machineId, int testStreamSwitch, int rotationAngle) {
        //保存数据

        Timestamp current = commonService.getCurrentTime();
        MachineSet set = machineSetDao.findBymachineId(machineId);
        set.setTestStreamSwitch(testStreamSwitch);
        set.setRotationAngle(rotationAngle);
        set.setUpdateTime(current);
        machineSetDao.save(set);
        //entityManager.clear();

        //发送消息给机器
        MachinePushStreamDto pushStreamDto = new MachinePushStreamDto();
        pushStreamDto.setCmd("setting_push_stream"); //设置推流
        pushStreamDto.setVmcNo(set.getMachine().getSn());
        Agent agent = set.getMachine().getAgent();
        if (null != agent) {
            pushStreamDto.setAppid(Long.valueOf(agent.getZegoId()));
            pushStreamDto.setAppsecret(agent.getZegoKey());
        }
        pushStreamDto.setRotationAngle(rotationAngle);
        pushStreamDto.setUseTestEnv(testStreamSwitch);
        messagePubService.pub(MessageKeyEnum.PUB_NETTY_DOLL.key, pushStreamDto);
        return new JsonMap(ExceptionCode.NORMAL.errorCode, "更新成功");
    }

    /**
     * 机器参数设置
     */
    @Transactional
    public synchronized JsonMap setParam(MachineSet machineSet, Byte encodeLevelSwitch, List<Machine> list) {
        Timestamp current = commonService.getCurrentTime();
        if (machineSet.getMachineId() != null && machineSet.getMachineId() > 0) {//当前机器设置
            MachineSet set = machineSetDao.findBymachineId(machineSet.getMachineId());
            Machine machine = machineDao.findOne(machineSet.getMachineId());
            if (set == null) {
                MachineSet newSet = this.putDataForAdd(machineSet, current);
                machineSetDao.save(newSet);
                this.send(newSet, encodeLevelSwitch, machine.getSn());

            } else {
                boolean flag = this.checkMachineVideoDto(set, machineSet, encodeLevelSwitch);
                if (!flag) {
                    this.send(set, machineSet, encodeLevelSwitch, machine.getSn());
                    this.putDataForEdit(set, machineSet, current);
                    machineSetDao.save(set);
                }

            }
        } else { //列表查询结果的所有机器设置

            for (Machine machine : list) {
                MachineSet set = machineSetDao.findBymachineId(machine.getId());
                if (set == null) {
                    machineSet.setMachineId(machine.getId());
                    MachineSet newSet = this.putDataForAdd(machineSet, current);
                    machineSetDao.save(newSet);
                    this.send(newSet, encodeLevelSwitch, machine.getSn());
                } else {
                    boolean flag = this.checkMachineVideoDto(set, machineSet, encodeLevelSwitch);
                    if (!flag) {
                        this.send(set, machineSet, encodeLevelSwitch, machine.getSn());
                        this.putDataForEdit(set, machineSet, current);
                        machineSetDao.save(set);

                    }
                }
            }
        }
        return new JsonMap();

    }

    /**
     * 机器推流设置
     *
     * @param dto
     */
    @Transactional
    public JsonMap setStreams(EnCodeLevelListDto dto) {
        Timestamp current = commonService.getCurrentTime();
        if (dto.getId().size() == dto.getEncodeLevel().size() && dto.getId().size() == dto.getMasterExposure().size()
                && dto.getId().size() == dto.getSlaveExposure().size()) {
        } else {
            return new JsonMap(ExceptionCode.PARAM_EXCEPTION);
        }

        //编辑保存
        for (int i = 0; i < dto.getId().size(); i++) {
            Integer id = dto.getId().get(i);
            MachineSet set = null;
            MachineSet ms = machineDao.findOne(id).getMachineSet();
            if (ms != null) {
                set = ms;
            } else {
                set = new MachineSet();
                set.setMachineId(id);
                set.setCreateTime(current);
            }
            set.setMasterExposure(dto.getMasterExposure().get(i));
            set.setSlaveExposure(dto.getSlaveExposure().get(i));
            set.setEncodeLevel(dto.getEncodeLevel().get(i));
            set.setUpdateTime(current);
            machineSetDao.save(set);
        }


        for (int i = 0; i < dto.getId().size(); i++) {
            Machine machine = machineDao.findOne(dto.getId().get(i));
//            MachineVideoDto machineVideoDto = new MachineVideoDto();
//            machineVideoDto.setCmd("setting_video");
//            machineVideoDto.setVmcNo(machine.getSn());
//            if (dto.getEncodeLevel().get(i) != null) {
//                machineVideoDto.setEncodeLevel(dto.getEncodeLevel().get(i));
//            }
//            machineVideoDto.setSlaveExposure(dto.getSlaveExposure().get(i));
//            machineVideoDto.setMasterExposure(dto.getSlaveExposure().get(i));
//            machineVideoDto.setOpenExposureSetting(1);


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, Object> map= new LinkedMultiValueMap<>();

            if (dto.getEncodeLevel().get(i) != null) {
                map.add("encodeLevel", converToString(dto.getEncodeLevel().get(i)));
            }

            map.add("masterExposure", converToString(dto.getSlaveExposure().get(i)));
            map.add("slaveExposure", converToString(dto.getSlaveExposure().get(i)));
            map.add("openExposureSetting", "1");

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
            sendPostRequest(machine.getSn(), "/machine/" + machine.getSn() + "/setting/camera", request);
        }
        return new JsonMap();
    }

    @Transactional
    public void deleteIds(Integer[] ids) {
        for (int id : ids) {
            machineDao.updateFlag(id, EnumFlag.Deleted.value);
        }
    }


    /**
     * 设置机器参数
     *
     * @return
     */
    public void setTop(Integer[] ids, Integer type) {
        for (Integer id : ids) {
            Machine machine = machineDao.findOne(id);
            machine.setIsTop(type);
            machineDao.save(machine);
        }
    }

    @Transactional
    public void updateFixStatus(Integer[] ids, int val) {
        // 更新数据库的维护状态
        machineDao.updateFixStatus(val, ids);

        for (Integer id : ids) {
            Machine machine = machineDao.findOne(id);
            RoomDto roomDto = redisService.get("ROOM_CACHE", machine.getSn(), RoomDto.class);
            roomDto.setFixStatus(val);
            if (val == 0) {
                roomDto.setFrequency(0);
                roomDto.setStatus(0);
            }
            redisService.set("ROOM_CACHE", machine.getSn(), roomDto);
        }
    }


    /**
     * 设置机器参数
     *
     * @return
     */
    public void choosePro(Integer id, Integer productId) {
        Machine machine = machineDao.findOne(id);
        machine.setProductId(productId);
        machineDao.save(machine);
    }

    public Machine findBySn(String sn) {
        return machineDao.findBySn(sn);
    }

    @Transactional
    public void setMachineAgent(Integer agentId, Integer machineId) {
        Machine machine = machineDao.findOne(machineId);
        if (machine != null) {
            machine.setAgentId(agentId);
            machineDao.save(machine);
        }

    }

    @Transactional
    public void updateSort(int id, int sort) {
        machineDao.updateSort(id, sort);

    }

    /*@Transactional
    public void updateType(int id) {
        Machine machine = machineDao.findOne(id);
        machine.setMachineType(machine.getMachineType().intValue()==1 ? 2 : 1);
        machineDao.save(machine);

    }*/

    @Transactional
    public JsonMap checkMark(int id, String mark) {
        Machine machine = machineDao.getByMark(mark);
        if (machine != null && machine.getId() != id) {
            return new JsonMap(ExceptionCode.MACHINE_MARK_EXSIT);
        }
        return new JsonMap(0, "校验成功，可以使用");

    }

    public JsonMap setMachine(int type,int machineId) {
        Machine machine = machineDao.findOne(machineId);
        String mes = "";
        if (type == 1) {//重启机器
//            RebootDto rebootDto = new RebootDto();
//            rebootDto.setCmd("reboot");
//            rebootDto.setVmcNo(machine.getSn());
            mes = "重启机器成功";
//            messagePubService.pub(MessageKeyEnum.PUB_NETTY_DOLL.key, rebootDto);

            restTemplate.postForObject("http://" + MachineGlobalCache.machineServiceMap.get(machine.getSn()) + "/machine/" + machine.getSn() + "/reboot", null, Result.class);

        } else if (type == 2) {//关闭机器
//            ShutdownDto shutdownDto = new ShutdownDto();
//            shutdownDto.setCmd("shutdown");
//            shutdownDto.setVmcNo(machine.getSn());
            mes = "关闭机器成功";
//            messagePubService.pub(MessageKeyEnum.PUB_NETTY_DOLL.key, shutdownDto);

            restTemplate.postForObject("http://" + MachineGlobalCache.machineServiceMap.get(machine.getSn()) + "/machine/" + machine.getSn() + "/shutdown", null, Result.class);
        }
        return new JsonMap(ExceptionCode.NORMAL.errorCode,mes);
    }

    public JsonMap addParamAttribute(Model model, Integer id) {
        Machine machine = null;
        MachineSet mset = null;
        Product product = null;
        //false:表示是全部设置   true:表示单个机器设置
        boolean oneMachineFlag = false;
        Agent agent = null;
        if (id != null && id > 0) {
            machine = machineDao.findOne(id);
            if (machine == null) {
                return new JsonMap(ExceptionCode.DATA_ERROR);
            }
            oneMachineFlag = true;
            mset = machineSetDao.findBymachineId(id);
            product = productService.get(machine.getProductId());
            agent = machine.getAgent();

        }
        model.addAttribute("modes", EnumexposureMode.values());
        MachineSet set = new MachineSet();
        set.setEncodeLevel(EnumEncodeLevel.C.value);
        set.setMasterExposure(120);
        set.setSlaveExposure(120);
        model.addAttribute("set", set);
        if (mset != null) {
            model.addAttribute("set", mset);
        }
        model.addAttribute("product", product);
        model.addAttribute("levels", EnumEncodeLevel.values());
        model.addAttribute("userDefined", EnumEncodeLevel.userDefined.value);

        //推流的参数
        model.addAttribute("angles", EnumStreamAngleOfRotationType.values());
        if (agent == null) {
            agent = new Agent();
        }
        model.addAttribute("agent", agent);
        model.addAttribute("oneMachineFlag", oneMachineFlag);
        return new JsonMap();


    }

    @Transactional
    public JsonMap updateMark(int id, String mark) {
        Machine machine = machineDao.getByMark(mark);
        if (machine != null && machine.getId() != id) {
            return new JsonMap(ExceptionCode.MACHINE_MARK_EXSIT);
        }
        machineDao.updateMark(id, mark);
        return new JsonMap(0, "更新成功");

    }
}
