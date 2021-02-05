package com.twkf.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description= "维修记录")
public class TbRepairRecord {
  @ApiModelProperty(value="序号",name="rr_id",dataType = "String")
  private String rr_id;
  @ApiModelProperty(value="送修时间（改：报修日期）",name="rr_time",dataType = "String")
  private String rr_time;
  @ApiModelProperty(value="维修地点ID",name="ra_id",dataType = "String")
  private String ra_id;
  @ApiModelProperty(value="维修员工ID",name="user_id",dataType = "String")
  private String user_id;
  @ApiModelProperty(value="维修方式ID",name="rt_id",dataType = "String")
  private String rt_id;
  @ApiModelProperty(value="维修内容",name="rc_content",dataType = "String")
  private String rc_content;
  @ApiModelProperty(value="维修费用",name="rr_cost",dataType = "String")
  private String rr_cost;
  @ApiModelProperty(value="审核状态",name="rr_state",dataType = "String")
  private String rr_state;
  @ApiModelProperty(value="维修车辆ID",name="vehi_id",dataType = "String")
  private String vehi_id;
  @ApiModelProperty(value="备注",name="note",dataType = "String")
  private String note;
  @ApiModelProperty(value="完修时间（改：维修日期）",name="rr_time_end",dataType = "String")
  private String rr_time_end;
  @ApiModelProperty(value="故障现象（改：维修类型）",name="rm_malfunction",dataType = "String")
  private String rm_malfunction;
  @ApiModelProperty(value="审核人ID",name="rr_assessor",dataType = "String")
  private String rr_assessor;
  @ApiModelProperty(value="审核时间",name="rr_auditime",dataType = "String")
  private String rr_auditime;
  @ApiModelProperty(value="记录状态",name="modify_status",dataType = "String")
  private String modify_status;
  @ApiModelProperty(value="维修员工用户名",name="user_name",dataType = "String")
  private String user_name;
  @ApiModelProperty(value="市民卡维修",name="citizen",dataType = "String")
  private String citizen;
  @ApiModelProperty(value="客户满意度",name="tcss",dataType = "String")
  private String tcss;
  @ApiModelProperty(value="故障类型id",name="rm_id",dataType = "String")
  private String rm_id;
  @ApiModelProperty(value="送修图片",name="rr_pict",dataType = "String")
  private String rr_pict;
  @ApiModelProperty(value="完修图片",name="rr_end_pict",dataType = "String")
  private String rr_end_pict;
  @ApiModelProperty(value="车辆联系电话",name="vehi_phone",dataType = "String")
  private String vehi_phone;
  @ApiModelProperty(value="车辆联系人(报修人员)",name="vehi_name",dataType = "String")
  private String vehi_name;
  @ApiModelProperty(value="维修单图片",name="rr_wxd_pict",dataType = "String")
  private String rr_wxd_pict;
  @ApiModelProperty(value="车牌号码",name="vehi_no",dataType = "String")
  private String vehi_no;
//  @ApiModelProperty(value="其他内容",name="other_content",dataType = "String")
//  private String other_content;
//  @ApiModelProperty(value="配件价格",name="parts_total_price",dataType = "String")
//  private String parts_total_price;
//  @ApiModelProperty(value="工时费工（免费：0元、检测：30元、驻点拆装：160元、上门拆装：200元）",name="labor_cost",dataType = "String")
//  private String labor_cost;
//  @ApiModelProperty(value="支付方式 (现金，支付宝，微信，支票)",name="pay_method",dataType = "String")
//  private String pay_method;
//  @ApiModelProperty(value="开票方式（发票，收据）",name="billing_method",dataType = "String")
//  private String billing_method;
//  @ApiModelProperty(value="发票编号",name="invoice_number",dataType = "String")
//  private String invoice_number;
//  @ApiModelProperty(value="0:未交费  1：已交费",name="pay_status",dataType = "String")
//  private String pay_status;
//  @ApiModelProperty(value="所属公司",name="company_name",dataType = "String")
//  private String company_name;
//  @ApiModelProperty(value="报修地点",name="report_address",dataType = "String")
//  private String report_address;
//  @ApiModelProperty(value="终端类型",name="mdt_type",dataType = "String")
//  private String mdt_type;
  @ApiModelProperty(value="报修人员",name="report_person",dataType = "String")
  private String report_person;
  @ApiModelProperty(value="紧急程度",name="emergency_procedures",dataType = "String")
  private String emergency_procedures;
  @ApiModelProperty(value="维修地点(app)",name="repair_address",dataType = "String")
  private String repair_address;
  @ApiModelProperty(value="配件使用情况",name="accessories_usage",dataType = "String")
  private String accessories_usage;
  @ApiModelProperty(value="费用收费方式",name="charge_method",dataType = "String")
  private String charge_method;
  @ApiModelProperty(value="是否修复（ 0:修复、1：未修复   2：无操作）",name="is_repaired",dataType = "String")
  private String is_repaired;
  @ApiModelProperty(value="是否转单 0:未转单  1：已转单",name="is_transfer_order",dataType = "String")
  private String is_transfer_order;
  @ApiModelProperty(value="转单状态（ 0:待确认、1：已确认   2：无操作）",name="transfer_order_status",dataType = "String")
  private String transfer_order_status;
  @ApiModelProperty(value="维修人员联系电话",name="repair_phone_number",dataType = "String")
  private String repair_phone_number;

  @ApiModelProperty(value="故障类型",name="rm_malfunction2",dataType = "String")
  private String rm_malfunction2;

  @ApiModelProperty(value="维修人员姓名",name="real_name",dataType = "String")
  private String real_name;
  @ApiModelProperty(value="维修人员用户名",name="user_name_wx",dataType = "String")
  private String user_name_wx;
  @ApiModelProperty(value="维修方式",name="rt_type",dataType = "String")
  private String rt_type;
  @ApiModelProperty(value="维修地址",name="ra_addr",dataType = "String")
  private String ra_addr;
  @ApiModelProperty(value="配件",name="partsandnumber",dataType = "String")
  private String partsandnumber;

  @ApiModelProperty(value="sim卡号",name="sim_num",dataType = "String")
  private String sim_num;
  @ApiModelProperty(value="终端类型",name="mt_name",dataType = "String")
  private String mt_name;
  @ApiModelProperty(value="区块",name="owner_name",dataType = "String")
  private String owner_name;
  @ApiModelProperty(value="公司",name="comp_name",dataType = "String")
  private String comp_name;
  @ApiModelProperty(value="终端子类型",name="mdt_sub_type",dataType = "String")
  private String mdt_sub_type;

}
