package com.twkf.service;

import com.twkf.dao.CommonDao;
import com.twkf.entity.BackMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/10/26 - 19:20
 */
@Service
@Slf4j
public class CommonService {

    @Autowired
    CommonDao commonDao;

    private Map<String,Object> getParameterToMap(HttpServletRequest request, String... parameters){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        for(String parameter : parameters){
            map.put(parameter,request.getParameter(parameter)==null?"":request.getParameter(parameter));
        }
        return map;
    }

    public BackMessage<Object> login(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"user_name","user_pwd");
        try{
            List<Map<String, Object>> list = commonDao.login(map);
            if(list.size() == 1){
                request.getSession().setAttribute("username", list.get(0).get("user_name"));
                request.getSession().setAttribute("user_id", list.get(0).get("user_id"));
                request.getSession().setMaxInactiveInterval(-1);
                return new BackMessage<Object>(200, "成功", list);
            }else if(list.size() > 1){
                return new BackMessage<Object>(400, "用户名多个", list);
            }else {
                return new BackMessage<Object>(400, "失败", null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
    }

    public BackMessage<Object> findUser(HttpServletRequest request) {
        try{
            List<Map<String, Object>> list = commonDao.findUser();
            return new BackMessage<Object>(200, "成功", list);
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
    }

    public BackMessage<Object> findRepairType(HttpServletRequest request) {
        try{
            List<Map<String, Object>> list = commonDao.findRepairType();
            return new BackMessage<Object>(200, "成功", list);
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
    }

    public BackMessage<Object> findRepairMalfunction(HttpServletRequest request) {
        try{
            List<Map<String, Object>> list = commonDao.findRepairMalfunction();
            return new BackMessage<Object>(200, "成功", list);
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
    }

    public BackMessage<Object> findRepairContent(HttpServletRequest request) {
        try{
            List<Map<String, Object>> list = commonDao.findRepairContent();
            return new BackMessage<Object>(200, "成功", list);
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public BackMessage<Object> userSign(HttpServletRequest request) {
        ReentrantLock lock = new ReentrantLock();
        Map<String, Object> map = getParameterToMap(request,"user_name", "user_id", "phone", "px", "py");
        try{
            //实时表更新,先删除后插入
            lock.lock();
            commonDao.deleteSignRealtime(map);
            Integer realtime_result = commonDao.insertSignRealtime(map);
            if(realtime_result<=0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new BackMessage<Object>(400, "签到失败", null);
            }
            //历史表更新
            Integer Transfered_result = commonDao.insertSignHistory(map);
            if(Transfered_result<=0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new BackMessage<Object>(400, "签到失败", null);
            }
            return new BackMessage<Object>(200, "成功", null);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }finally {
            lock.unlock();
        }
    }
}


