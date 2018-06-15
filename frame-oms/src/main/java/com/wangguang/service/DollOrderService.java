package com.wangguang.service;


import com.google.common.collect.Maps;
import com.wangguang.core.utils.ExcelUtils;
import com.wangguang.dao.OrderDao;
import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.Agent;
import com.wangguang.model.entity.Order;
import com.wangguang.model.entity.OrderProduct;
import com.wangguang.model.entity.member.Member;
import com.wangguang.model.enums.EnumExpress;
import com.wangguang.model.enums.EnumPlatform;
import com.wangguang.model.enums.EnumPushType;
import com.wangguang.services.CommonService;
import com.wangguang.services.ExceptionCode;
import com.wangguang.web.JsonMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@Service
public class DollOrderService extends BaseService<Order,Integer> {

    private OrderDao orderDao;

    @Resource
    private CommonService commonService;

    @Resource
    private AgentService agentService;

    @Resource
    private MemberService memberService;

    /*@Resource
    private MessageService messageService;*/

    @Override
    @Resource
    public void setBaseDao(BaseDao<Order, Integer> baseDao) {
        this.baseDao = baseDao;
        orderDao = (OrderDao)baseDao;
    }


    public Order findByOrderSn(String orderSn){
        return orderDao.findByOrderSn(orderSn);
    }

    /**
     * 发货
     */
    @Transactional
    public void deliver(String orderSn,String expressNo,int type,String expressName){
       /* Order order = orderDao.findByOrderSn(orderSn);
        if(order == null)
            return;
        Member member = memberService.findMember(order.getMemberId());
        if(member == null)
            return;
        Agent agent = agentService.findById(member.getAgentId());

        String name = EnumExpress.getLabel((byte) type);
        if(EnumExpress.OTHERS.value == type){
            name = expressName;
        }
        orderDao.updateOrderExpress(expressNo,commonService.getCurrentTime(),(byte)type,(byte)0,name,orderSn);
        Map<String,String> param = Maps.newHashMap();
        param.put("type", EnumPushType.FAHUO.value+"");
        param.put("orderSn",orderSn);

        if(StringUtils.isNotEmpty(agent.getJpushSecret()) &&
                StringUtils.isNotEmpty(agent.getJpushKey())){
            if(member.getPlatform().equals(EnumPlatform.Android.value)||member.getPlatform().equals(EnumPlatform.IOS.value)){
                JPushUtils jPushUtils = JPushUtils.getInstance(agent.getJpushSecret(),agent.getJpushKey());
                jPushUtils.broadcastPushWithAlias("发货通知","您的订单号:"+orderSn+"已发货",param,member.getAliasId());
            }
        }

        Message message = new Message();
        message.setType(MessageTypeEnum.ORDER.value);
        message.setTitle("订单通知");
        message.setMessage("您的商品发货\n"+name+":"+expressNo);
        message.setOrderSn(orderSn);
        message.setAgentId(member.getAgentId());
        message.setMemberId(member.getId());
        messageService.save(message);*/
    }

    public void setProductName(List<Order> orders){
        if(CollectionUtils.isNotEmpty(orders)){
            for(Order order : orders){
                if(CollectionUtils.isNotEmpty(order.getOrderProducts())){
                    String productName = "";
                    int num = 0;
                    for(OrderProduct op : order.getOrderProducts()){
                        productName += op.getProduct().getName()+"*"+op.getNum()+"    ";
                        num += op.getNum();
                    }
                    order.setProductName(productName);
                    order.setProductNum(num);
                }

            }
        }

    }


    @Transactional
    public JsonMap uploadExcel(MultipartFile[] fileData) {
        try {
            if (fileData != null && fileData.length > 0) {
                // 循环获取file数组中得文件
                for (int i = 0; i < fileData.length; i++) {
                    MultipartFile file = fileData[i];

                    List<Row> list = new ExcelUtils().parseExcelToRows(file.getInputStream());
                    for(int j=0;j < list.size();j++){
                        Row row = list.get(j);
                        if(j==0){//过滤标题
                            continue;
                        }
                        //订单号校验
                        if(row.getCell(0).getCellType()!=1){
                            return new JsonMap(ExceptionCode.EXCEL_CELL_ORDER_SN_ERROR);
                        }else{
                            if(row.getCell(0).getStringCellValue().trim().length()!=18){
                                return new JsonMap(ExceptionCode.EXCEL_CELL_ORDER_SN_ERROR);
                            }
                        }

                        //物流公司校验
                        if(row.getCell(1).getCellType()!=0){
                            return new JsonMap(ExceptionCode.EXCEL_CELL_EXPRESS_TYPE_FORMAT_ERROR);
                        }else{
                            String regex="^[1-9]+[0-9]*$";
                            //^[1-9]+\\d*$
                            Pattern p= Pattern.compile(regex);
                            Double  expressType = row.getCell(1).getNumericCellValue();
                            if(EnumExpress.getLabel(expressType.byteValue())==null){
                                return new JsonMap(ExceptionCode.EXCEL_CELL_EXPRESS_TYPE_DATA_ERROR);
                            }
                        }

                        //运单号校验
                        if(row.getCell(2).getCellType()!=1){
                            return new JsonMap(ExceptionCode.EXCEL_CELL_EXPRESS_NO_ERROR);
                        }

                        if(StringUtils.isEmpty(row.getCell(0).getStringCellValue())){
                            continue;
                        }
                        String orderSn = row.getCell(0).getStringCellValue().trim();
                        Order order = orderDao.findByOrderSn(orderSn);
                        if(order==null){
                            continue;
                        }
                        byte type = (byte)row.getCell(1).getNumericCellValue();
                        if(EnumExpress.getLabel(type)==null){
                            return new JsonMap(ExceptionCode.EXPRESS_COMPANY_ERROR);
                        }
                        order.setType((byte)row.getCell(1).getNumericCellValue());
                        order.setExpressNo(row.getCell(2).getStringCellValue());
                        orderDao.save(order);

                    }
                }
            }
        } catch (Exception e) {
            return new JsonMap(ExceptionCode.EXCEL_FILE_STREAM_ERROR);
        }
        return new JsonMap(ExceptionCode.NORMAL);
    }


}
