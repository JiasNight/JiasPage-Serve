package com.jias.page.controller;

import com.jias.page.utils.redisUtil.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取对应键的值，redisTemplate操作
     * @param key
     * @return
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Object getValue(String key){
        Object value = redisTemplate.opsForValue().get(key);
        return value;
    }

    /**
     * 添加键值
     * @param key
     * @param value
     * @return
     */
    @RequestMapping(value = "set", method = RequestMethod.GET)
    public boolean set(String key, String value){
        return redisUtil.set(key, value);
    }

    /**
     * 添加List对象数据到redis中
     * @return
     */
    @RequestMapping(value = "/setList", method = RequestMethod.GET)
    public boolean setList(){
        List<Object> stuList = new ArrayList<>();
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        stuList.add(user);
        return redisUtil.lSetList("user", stuList);
    }

    /**
     * 获取全部数据
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public Object getList(){
        return redisUtil.lGet("user", 0, -1);
    }

}
