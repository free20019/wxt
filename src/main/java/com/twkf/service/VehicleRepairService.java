package com.twkf.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.twkf.dao.VehicleRepairDao;
import com.twkf.entity.BackMessage;
import com.twkf.entity.TbRepairRecord;
import com.twkf.helper.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/10/22 - 14:54
 */
@Service
@Slf4j
public class VehicleRepairService {

    @Autowired
    VehicleRepairDao vehicleRepairDao;

    private JacksonUtil jacksonUtil =JacksonUtil.buildNormalBinder();


    private Map<String,Object> getParameterToMap(HttpServletRequest request, String... parameters){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        for(String parameter : parameters){
            map.put(parameter,request.getParameter(parameter)==null?"":request.getParameter(parameter));
        }
        return map;
    }

    public BackMessage<Object> test() {
        return new BackMessage<Object>(200, "成功", vehicleRepairDao.test());
    }

    @Transactional(rollbackFor = Exception.class)
    public BackMessage<Object> findRepairStatusNumber(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"stime","etime","user_id","see_all");
        Map<String, Object> result = new HashMap<>();
        try{
            //待维修
            Integer toBeRepaired = vehicleRepairDao.toBeRepairedCount(map);
            //已维修
            Integer repaired = vehicleRepairDao.repairedCount(map);
            //待确认
            Integer toBeConfirmed = vehicleRepairDao.toBeConfirmedCount(map);
            result.put("toBeRepaired",toBeRepaired);
            result.put("repaired",repaired);
            result.put("toBeConfirmed",toBeConfirmed);
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
        return new BackMessage<Object>(200, "成功", result);
    }

    public BackMessage<Object> findToBeRepaired(HttpServletRequest request) {
        Integer page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());
        Integer page_size = request.getParameter("page_size")==null?10:Integer.parseInt(request.getParameter("page_size").toString());
        Map<String, Object> map = getParameterToMap(request,"stime","etime","user_id","see_all");
        Map<String, Object> result = new HashMap<>();
        try{
            PageHelper.startPage(page, page_size);
            List<Map<String, Object>> list = vehicleRepairDao.findToBeRepaired(map);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
            result.put("count",pageInfo.getTotal());
            result.put("list",pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
        return new BackMessage<Object>(200, "成功", result);
    }

    public BackMessage<Object> findRepaired(HttpServletRequest request) {
        Integer page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());
        Integer page_size = request.getParameter("page_size")==null?10:Integer.parseInt(request.getParameter("page_size").toString());
        Map<String, Object> map = getParameterToMap(request,"stime","etime","user_id","see_all");
        Map<String, Object> result = new HashMap<>();
        try{
            PageHelper.startPage(page, page_size);
            List<Map<String, Object>> list = vehicleRepairDao.findRepaired(map);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);

//            System.out.println("总数量：" + pageInfo.getTotal());
//            System.out.println("当前页查询记录：" + pageInfo.getList().size());
//            System.out.println("当前页码：" + pageInfo.getPageNum());
//            System.out.println("每页显示数量：" + pageInfo.getPageSize());
//            System.out.println("总页：" + pageInfo.getPages());
            result.put("count",pageInfo.getTotal());
            result.put("list",pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
        return new BackMessage<Object>(200, "成功", result);
    }

    public BackMessage<Object> findToBeConfirmed(HttpServletRequest request) {
        Integer page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());
        Integer page_size = request.getParameter("page_size")==null?10:Integer.parseInt(request.getParameter("page_size").toString());
        Map<String, Object> map = getParameterToMap(request,"stime","etime","user_id","see_all");
        Map<String, Object> result = new HashMap<>();
        try{
            PageHelper.startPage(page, page_size);
            List<Map<String, Object>> list = vehicleRepairDao.findToBeConfirmed(map);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
            result.put("count",pageInfo.getTotal());
            result.put("list",pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
        return new BackMessage<Object>(200, "成功", result);
    }

    @Transactional(rollbackFor = Exception.class)
    public BackMessage<Object> transferOrder(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"rr_id","report_address","user_id","repair_phone_number");
//        if("".equals(map.get("repair_phone_number"))){
//            map.put("repair_phone_number","");
//        }
        try{
            //判断记录状态
            List<Map<String, Object>> orderStatus = vehicleRepairDao.findOrderStatus(map);
            if(orderStatus.size()==0||!"正常".equals(orderStatus.get(0).get("STATUS"))){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                if(orderStatus.size()==0){
                    return new BackMessage<Object>(400, "无该条纪录", null);
                }else{
                    return new BackMessage<Object>(400, "该纪录为‘"+orderStatus.get(0).get("STATUS")+"’", null);
                }
            }
            //copy一个新纪录，更改新纪录的相关字段
            Integer copy_result = vehicleRepairDao.copyRepairRecord(map);
            if(copy_result<=0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new BackMessage<Object>(400, "失败", null);
            }
            //更改原纪录为‘已转单’
            Integer Transfered_result = vehicleRepairDao.TransferedOrder(map);
            if(Transfered_result<=0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new BackMessage<Object>(400, "该纪录为‘已转单’或‘已维修’", null);
            }
            return new BackMessage<Object>(200, "成功", null);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
    }

    public BackMessage<Object> changeTransferOrderStatus(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"rr_id","user_id","transfer_order_status");
        try{
            Integer count = vehicleRepairDao.changeTransferOrderStatus(map);
            if(count>0){
                return new BackMessage<Object>(200, "成功", null);
            }else{
                return new BackMessage<Object>(400, "失败", null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
    }

    public BackMessage<Object> commitRepair(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"rr_id","rr_time_end","repair_address","rm_id","rm_malfunction","rt_id","rc_content","accessories_usage","rr_cost","charge_method","is_repaired");
        try{
            Integer count = vehicleRepairDao.commitRepair(map);
            if(count>0){
                return new BackMessage<Object>(200, "成功", null);
            }else{
                return new BackMessage<Object>(400, "失败", null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
    }
}
