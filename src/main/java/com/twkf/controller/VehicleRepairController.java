package com.twkf.controller;

import com.twkf.entity.BackMessage;
import com.twkf.entity.TbRepairRecord;
import com.twkf.service.VehicleRepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/10/22 - 14:53
 */
@Controller
@RequestMapping("/vehicleRepair")
@Slf4j
@Api(tags = "维修接口")
public class VehicleRepairController {

    @Autowired
    VehicleRepairService vehicleRepairService;

    @GetMapping("/test")
    @ResponseBody
    @ApiOperation(value = "测试", hidden = true)
    public BackMessage<Object> test(){
        return vehicleRepairService.test();
    }

    @GetMapping("/test1")
    @ResponseBody
    @ApiOperation(value = "实体类显示", hidden = true)
    public TbRepairRecord test1(){
        return new TbRepairRecord();
    }

    @GetMapping("/findRepairStatusNumber")
    @ResponseBody
    @ApiOperation(value = "维修状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stime", value = "开始日期（例：2020-10-01）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "etime", value = "结束日期（例：2020-10-02）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "see_all", value = "查询限制(0:查询全部记录, 1:查询当前用户记录)", required = true, dataType = "Integer"),
    })
    public BackMessage<Object> findRepairStatusNumber(HttpServletRequest request){
        return vehicleRepairService.findRepairStatusNumber(request);
    }

    @GetMapping("/findToBeRepaired")
    @ResponseBody
    @ApiOperation(value = "待维修清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stime", value = "开始日期（例：2020-10-01）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "etime", value = "结束日期（例：2020-10-02）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "see_all", value = "查询限制(0:查询全部记录, 1:查询当前用户记录)", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page_size", value = "每页显示条数", required = true, dataType = "Integer"),
    })
    public BackMessage<Object> findToBeRepaired(HttpServletRequest request){
        return vehicleRepairService.findToBeRepaired(request);
    }

    @GetMapping("/findRepaired")
    @ResponseBody
    @ApiOperation(value = "已维修清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stime", value = "开始日期（例：2020-10-01）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "etime", value = "结束日期（例：2020-10-02）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "see_all", value = "查询限制(0:查询全部记录, 1:查询当前用户记录)", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page_size", value = "每页显示条数", required = true, dataType = "Integer"),
    })
    public BackMessage<Object> findRepaired(HttpServletRequest request){
        return vehicleRepairService.findRepaired(request);
    }

    @GetMapping("/findToBeConfirmed")
    @ResponseBody
    @ApiOperation(value = "待确认清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stime", value = "开始日期（例：2020-10-01）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "etime", value = "结束日期（例：2020-10-02）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "see_all", value = "查询限制(0:查询全部记录, 1:查询当前用户记录)", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page_size", value = "每页显示条数", required = true, dataType = "Integer"),
    })
    public BackMessage<Object> findToBeConfirmed(HttpServletRequest request){
        return vehicleRepairService.findToBeConfirmed(request);
    }

    @GetMapping("/transferOrder")
    @ResponseBody
    @ApiOperation(value = "转单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rr_id", value = "维修记录id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "report_address", value = "报修地点", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user_id", value = "维修员工ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "repair_phone_number", value = "维修人员电话号码（用户下拉框中）", dataType = "String"),
    })
    public BackMessage<Object> transferOrder(HttpServletRequest request){
        return vehicleRepairService.transferOrder(request);
    }

    @GetMapping("/changeTransferOrderStatus")
    @ResponseBody
    @ApiOperation(value = "改变转单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rr_id", value = "维修记录id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user_id", value = "维修员工ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "transfer_order_status", value = "转单状态（1：已确认）", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "transfer_order_status", value = "转单状态（ 0:待确认、1：已确认 2：无操作）", required = true, dataType = "String"),
    })
    public BackMessage<Object> changeTransferOrderStatus(HttpServletRequest request){
        return vehicleRepairService.changeTransferOrderStatus(request);
    }

    @GetMapping("/commitRepair")
    @ResponseBody
    @ApiOperation(value = "维修")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rr_id", value = "维修记录id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rr_time_end", value = "维修日期（例：2020-10-13 15:04:10）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "repair_address", value = "维修地图(维修地点)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rm_id", value = "故障类型id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rm_malfunction", value = "维修类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rt_id", value = "维修方式id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rc_content", value = "维修内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "accessories_usage", value = "配件使用情况", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rr_cost", value = "维修费用", required = true, dataType = "String"),
            @ApiImplicitParam(name = "charge_method", value = "费用收取方式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "is_repaired", value = "是否修复（ 0:修复  1：未修复 2：无操作）", required = true, dataType = "String"),
    })
    public BackMessage<Object> commitRepair(HttpServletRequest request){
        return vehicleRepairService.commitRepair(request);
    }
}
