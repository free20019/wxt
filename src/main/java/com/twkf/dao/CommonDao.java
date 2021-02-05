package com.twkf.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/10/26 - 19:21
 */
public interface CommonDao {

    @Select("select * from tb_user_wx where user_name=#{user_name} and user_pwd=#{user_pwd}")
    List<Map<String, Object>> login(Map<String, Object> map);

    @Select("select distinct user_name,user_id,real_name,app_power,phone_number from tb_user_wx where USER_NAME like 'wx%'")
    List<Map<String, Object>> findUser();

    @Select("select distinct * from TB_REPAIR_TYPE")
    List<Map<String, Object>> findRepairType();

    @Select("select distinct * from TB_REPAIR_MALFUNCTION")
    List<Map<String, Object>> findRepairMalfunction();

    @Select("select distinct * from TB_REPAIR_CONTENT")
    List<Map<String, Object>> findRepairContent();

    @Insert("insert into tb_repair_person_sign_realtime (user_name, user_id, phone, px, py) values (#{user_name}, #{user_id}, #{phone}, #{px}, #{py})")
    Integer insertSignRealtime(Map<String, Object> map);

    @Insert("insert into tb_repair_person_sign_history (user_name, user_id, phone, px, py) values (#{user_name}, #{user_id}, #{phone}, #{px}, #{py})")
    Integer insertSignHistory(Map<String, Object> map);

    @Delete("delete from tb_repair_person_sign_realtime where user_id = #{user_id}")
    Integer deleteSignRealtime(Map<String, Object> map);
}
