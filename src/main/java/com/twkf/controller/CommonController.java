package com.twkf.controller;

import com.twkf.entity.BackMessage;
import com.twkf.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/10/26 - 19:18
 */

@Controller
@RequestMapping("/vehicleRepair")
@Slf4j
@Api(tags = "下拉框，登录")
public class CommonController {

    @Autowired
    CommonService commonService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_name" ,value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "user_pwd" ,value = "密码",required = true,dataType = "String"),
    })
    public BackMessage<Object> login(HttpServletRequest request) {
        return commonService.login(request);
    }

    @GetMapping("/findUser")
    @ResponseBody
    @ApiOperation(value = "用户下拉框")
    @ApiImplicitParams({
    })
    public BackMessage<Object> findUser(HttpServletRequest request) {
        return commonService.findUser(request);
    }

    @GetMapping("/findRepairType")
    @ResponseBody
    @ApiOperation(value = "维修方式下拉框")
    @ApiImplicitParams({
    })
    public BackMessage<Object> findRepairType(HttpServletRequest request) {
        return commonService.findRepairType(request);
    }

    @GetMapping("/findRepairMalfunction")
    @ResponseBody
    @ApiOperation(value = "故障类型下拉框")
    @ApiImplicitParams({
    })
    public BackMessage<Object> findRepairMalfunction(HttpServletRequest request) {
        return commonService.findRepairMalfunction(request);
    }

    @GetMapping("/findRepairContent")
    @ResponseBody
    @ApiOperation(value = "维修类型下拉框")
    @ApiImplicitParams({
    })
    public BackMessage<Object> findRepairContent(HttpServletRequest request) {
        return commonService.findRepairContent(request);
    }

    @GetMapping("/userSign")
    @ResponseBody
    @ApiOperation(value = "用户签到")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_name" ,value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "user_id" ,value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "phone" ,value = "手机号码",required = true,dataType = "String"),
            @ApiImplicitParam(name = "px" ,value = "经度",required = true,dataType = "String"),
            @ApiImplicitParam(name = "py" ,value = "纬度",required = true,dataType = "String"),
    })
    public BackMessage<Object> userSign(HttpServletRequest request) {
        return commonService.userSign(request);
    }
}
