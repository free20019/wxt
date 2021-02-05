package com.twkf.dao;

import com.twkf.entity.TbRepairRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/10/22 - 14:55
 */
public interface VehicleRepairDao {

    @Select("select * from tb_user")
    List<Map<String, Object>> test();

    @Select("<script>" +
            " select count(*) count from TB_REPAIR_RECORD r" +
            " where to_char(r.RR_TIME,'yyyy-MM-dd') &gt;= #{stime} and to_char(r.RR_TIME,'yyyy-MM-dd') &lt;= #{etime} and r.RR_TIME_END is null and r.TRANSFER_ORDER_STATUS != 0  and r.is_transfer_order != 1" +
            " <if test='see_all!=null and see_all != \"\" and see_all != 0 '>" +
            " and r.USER_ID = #{user_id}" +
            " </if>" +
            "</script>")
    Integer toBeRepairedCount(Map<String, Object> map);

    @Select("<script>" +
            " select count(*) count from TB_REPAIR_RECORD r" +
            " where to_char(r.RR_TIME,'yyyy-MM-dd') &gt;= #{stime} and to_char(r.RR_TIME,'yyyy-MM-dd') &lt;= #{etime} and r.RR_TIME_END is not null and r.is_transfer_order != 1" +
            " <if test='see_all!=null and see_all != \"\" and see_all != 0 '>" +
            " and r.USER_ID = #{user_id}" +
            " </if>" +
            "</script>")
    Integer repairedCount(Map<String, Object> map);

    @Select("<script>" +
            " select count(*) count from TB_REPAIR_RECORD r" +
            " where to_char(r.RR_TIME,'yyyy-MM-dd') &gt;= #{stime} and to_char(r.RR_TIME,'yyyy-MM-dd') &lt;= #{etime} and r.TRANSFER_ORDER_STATUS = 0 and r.is_transfer_order != 1" +
            " <if test='see_all!=null and see_all != \"\" and see_all != 0 '>" +
            " and r.USER_ID = #{user_id}" +
            " </if>" +
            "</script>")
    Integer toBeConfirmedCount(Map<String, Object> map);

    @Select("<script>" +
            " select r.*,u.real_name,u.user_name user_name_wx,rt.rt_type,rm.rm_malfunction rm_malfunction2,ra.ra_addr,uw.real_name shry_name,v.sim_num,v.mt_name,v.owner_name,v.comp_name,v.mdt_sub_type" +
            " from TB_REPAIR_RECORD r" +
            " left join tb_user_wx u on r.user_id=u.user_id" +
            " left join TB_REPAIR_TYPE rt on r.rt_id=rt.rt_id" +
            " left join TB_REPAIR_MALFUNCTION rm on r.rm_id=rm.rm_id" +
            " left join TB_REPAIR_ADDR ra on r.ra_id = ra.ra_id" +
            " left join tb_user_wx uw on r.RR_ASSESSOR=uw.USER_ID " +
            " left join VW_VEHICLE_WX v on r.vehi_no = v.vehi_no" +
            " where to_char(r.RR_TIME,'yyyy-MM-dd') &gt;= #{stime} and to_char(r.RR_TIME,'yyyy-MM-dd') &lt;= #{etime} and r.RR_TIME_END is null and r.TRANSFER_ORDER_STATUS != 0 and r.is_transfer_order != 1" +
            " <if test='see_all!=null and see_all != \"\" and see_all != 0 '>" +
            " and r.USER_ID = #{user_id}" +
            " </if>" +
            " order by r.RR_TIME desc" +
            "</script>")
    List<Map<String, Object>> findToBeRepaired(Map<String, Object> map);

    @Select("<script>" +
            " select r.*,u.real_name,u.user_name user_name_wx,rt.rt_type,rm.rm_malfunction rm_malfunction2,ra.ra_addr,uw.real_name shry_name,v.sim_num,v.mt_name,v.owner_name,v.comp_name,v.mdt_sub_type" +
            " from TB_REPAIR_RECORD r" +
            " left join tb_user_wx u on r.user_id=u.user_id" +
            " left join TB_REPAIR_TYPE rt on r.rt_id=rt.rt_id" +
            " left join TB_REPAIR_MALFUNCTION rm on r.rm_id=rm.rm_id" +
            " left join TB_REPAIR_ADDR ra on r.ra_id = ra.ra_id" +
            " left join tb_user_wx uw on r.RR_ASSESSOR=uw.USER_ID " +
            " left join VW_VEHICLE_WX v on r.vehi_no = v.vehi_no" +
            " where to_char(r.RR_TIME,'yyyy-MM-dd') &gt;= #{stime} and to_char(r.RR_TIME,'yyyy-MM-dd') &lt;= #{etime} and r.RR_TIME_END is not null and r.is_transfer_order != 1" +
            " <if test='see_all!=null and see_all != \"\" and see_all != 0 '>" +
            " and r.USER_ID = #{user_id}" +
            " </if>" +
            " order by r.RR_TIME desc" +
            "</script>")
    List<Map<String, Object>> findRepaired(Map<String, Object> map);

    @Select("<script>" +
            " select r.*,u.real_name,u.user_name user_name_wx,rt.rt_type,rm.rm_malfunction rm_malfunction2,ra.ra_addr,uw.real_name shry_name,v.sim_num,v.mt_name,v.owner_name,v.comp_name,v.mdt_sub_type" +
            " from TB_REPAIR_RECORD r" +
            " left join tb_user_wx u on r.user_id=u.user_id" +
            " left join TB_REPAIR_TYPE rt on r.rt_id=rt.rt_id" +
            " left join TB_REPAIR_MALFUNCTION rm on r.rm_id=rm.rm_id" +
            " left join TB_REPAIR_ADDR ra on r.ra_id = ra.ra_id" +
            " left join tb_user_wx uw on r.RR_ASSESSOR=uw.USER_ID " +
            " left join VW_VEHICLE_WX v on r.vehi_no = v.vehi_no" +
            " where to_char(r.RR_TIME,'yyyy-MM-dd') &gt;= #{stime} and to_char(r.RR_TIME,'yyyy-MM-dd') &lt;= #{etime} and r.TRANSFER_ORDER_STATUS = 0 and r.is_transfer_order != 1" +
            " <if test='see_all!=null and see_all != \"\" and see_all != 0 '>" +
            " and r.USER_ID = #{user_id}" +
            " </if>" +
            " order by r.RR_TIME desc" +
            "</script>")
    List<Map<String, Object>> findToBeConfirmed(Map<String, Object> map);

    @Update("update TB_REPAIR_RECORD set transfer_order_status = 0, report_address = #{report_address}, user_id = #{user_id} where rr_id = #{rr_id}")
    Integer transferOrder(Map<String, Object> map);

    @Update("update TB_REPAIR_RECORD set transfer_order_status = #{transfer_order_status} where user_id = #{user_id} and rr_id = #{rr_id}")
    Integer changeTransferOrderStatus(Map<String, Object> map);

    @Insert("insert into tb_repair_record (rr_id,rr_time,ra_id,user_id,rt_id,rc_content,rr_cost,rr_state,vehi_id,note,rr_time_end,rm_malfunction,rr_assessor,rr_auditime,modify_status,user_name,citizen,tcss,rm_id,rr_pict,rr_end_pict,vehi_phone,vehi_name,rr_wxd_pict,vehi_no,report_address,report_person,emergency_procedures,repair_address,accessories_usage,charge_method,is_repaired,is_transfer_order,transfer_order_status,repair_phone_number)" +
            " select (select max(to_number(rr_id))+1 from tb_repair_record),rr_time,ra_id,#{user_id},rt_id,rc_content,rr_cost,rr_state,vehi_id,note,rr_time_end,rm_malfunction,rr_assessor,rr_auditime,modify_status,user_name,citizen,tcss,rm_id,rr_pict,rr_end_pict,vehi_phone,vehi_name,rr_wxd_pict,vehi_no,#{report_address},report_person,emergency_procedures,repair_address,accessories_usage,charge_method,is_repaired,is_transfer_order,transfer_order_status,#{repair_phone_number}" +
            " from TB_REPAIR_RECORD where rr_id = #{rr_id}"
        )
    Integer copyRepairRecord(Map<String, Object> map);

    @Select("select case when RR_TIME_END is not null then '已维修' when is_transfer_order = 1 then '已转单' else '正常' end status from TB_REPAIR_RECORD where rr_id = #{rr_id}")
    List<Map<String, Object>> findOrderStatus(Map<String, Object> map);

    @Update("update TB_REPAIR_RECORD set is_transfer_order = 1 where rr_id = #{rr_id} and is_transfer_order != 1 and RR_TIME_END is null")
    Integer TransferedOrder(Map<String, Object> map);

    @Update("update TB_REPAIR_RECORD set" +
            " rr_time_end = to_date(#{rr_time_end},'yyyy-mm-dd hh24:mi:ss'),repair_address = #{repair_address},rm_id = #{rm_id},rm_malfunction = #{rm_malfunction},rt_id = #{rt_id}" +
            " ,rc_content = #{rc_content},accessories_usage = #{accessories_usage},rr_cost = #{rr_cost},charge_method = #{charge_method},is_repaired = #{is_repaired}" +
            " where rr_id = #{rr_id}")
    Integer commitRepair(Map<String, Object> map);
}