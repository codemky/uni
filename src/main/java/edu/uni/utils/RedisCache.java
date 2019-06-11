package edu.uni.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @Author 何亮
 * @date 2019/4/3
 */
@Component
public class RedisCache {
    // 日志组件
    private LogUtils logUtils = new LogUtils(RedisCache.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 通过字符串key获取值
     * @param key 键
     * @return 值
     */
    public String get(String key){
        return key==null ? null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key,String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logUtils.error("设置缓存失败：" + key);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置过期时间
     * @param key 键
     * @param value 值
     * @param seconds 秒数
     * @return true成功 false失败
     */
    public boolean set(String key,String value, long seconds) {
        try {
            redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            logUtils.logException(e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入，若已存在则不操作
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean setIfAbsent(String key,String value) {
        try {
            redisTemplate.opsForValue().setIfAbsent(key, value);
            return true;
        } catch (Exception e) {
            logUtils.logException(e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除对象
     * @param key
     */
    public void delete(String key){
        if(!redisTemplate.delete(key)){
            logUtils.error("删除缓存失败：" + key);
        }
    }

    /**
     * 根据通配符匹配进行删除
     * @param paterm
     */
    public void deleteByPaterm(String paterm){
        Set<String> keys = redisTemplate.keys(paterm);
        if(keys != null){
            Iterator<String> iterator = keys.iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                delete(key);
            }
        }
    }

    /**
     * 返回与通配符字符串匹配的所有键名
     * @param paterm
     * @return
     */
    public Set<String> keys(String paterm){
        return redisTemplate.keys(paterm);
    }


    /**
     * 功能描述：设置某个key过期时间
     * @param key
     * @param seconeds
     * @return
     */
    public boolean expire(String key,long seconeds){
        try {
            if(seconeds>0){
                redisTemplate.expire(key, seconeds, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logUtils.logException(e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 功能描述：根据key 获取过期时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }


    /**
     * 递增
     * @param key 键
     * @return
     */
    public long increment(String key, long delta){
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 递减
     * @param key 键
     * @param delta 要减少几
     * @return
     */
    public long decrement(String key, long delta){
        return redisTemplate.opsForValue().increment(key, -delta);
    }
}