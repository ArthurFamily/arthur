package com.cloud.arthur.config.redis;

import com.cloud.arthur.util.SerializeUtil;
import com.google.common.collect.Lists;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenzhen on 2017/2/22.
 */
@Repository
public class RedisDao {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> currentRedisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    public RedisDao() {
    }

    /**
     * 普通设置缓存key值.
     * @param key
     * @param value
     */
    public void valueSet(String key, String value) {
        this.valueOperations.set(key, value);
    }

    /**
     * 设置key.
     * @param key
     * @param value
     * @param exp 生命周期 秒
     */
    public void valueSet(String key, String value, long exp) {
        this.valueOperations.set(key, value, exp, TimeUnit.SECONDS);
    }

    /**
     * 取出key的值.
     * @param key
     * @return
     */
    public String valueGet(String key) {
        return (String)this.valueOperations.get(key);
    }

    /**
     * 验证key是否存在.
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return this.currentRedisTemplate.hasKey(key).booleanValue();
    }

    /**
     * redis删除key值.
     * @param key
     */
    public void delete(String key) {
        this.currentRedisTemplate.delete(key);
    }

    /**
     * redis批量删除key值.
     * @param keys
     */
    public void delete(List<String> keys) {
        if(!CollectionUtils.isEmpty(keys)) {
            ArrayList delKeys = Lists.newArrayList();
            Iterator var3 = keys.iterator();

            while(var3.hasNext()) {
                String key = (String)var3.next();
                delKeys.add(key);
            }

            this.currentRedisTemplate.delete(delKeys);
        }
    }

    /**
     * redis原子性操作.
     * @param key
     * @return 默认增长值1
     */
    public Long increment(String key) {
        return this.valueOperations.increment(key, 1L);
    }

    /**
     * redis原子性操作.
     * @param key
     * @param step 增长值
     * @return
     */
    public Long increment(String key, long step) {
        return this.valueOperations.increment(key, step);
    }

    /**
     * 链表左方进值.
     * @param key
     * @param value
     */
    public void leftPush(String key, String value) {
        this.listOps.leftPush(key, value);
    }

    /**
     * 链表右方进值.
     * @param key
     * @param value
     */
    public void rightPush(String key, String value) {
        this.listOps.rightPush(key, value);
    }

    /**
     * 链表左端取值删除.
     * @param key
     */
    public String leftPop(String key) {
        return (String)this.listOps.leftPop(key);
    }

    /**
     * 链表右端取值删除.
     * @param key
     */
    public String rightPop(String key) {
        return (String)this.listOps.rightPop(key);
    }

    /**
     * 存储序列化对象.
     * @param object
     */
    public void setObject(String key, Object object) {
        Jedis jedis = (Jedis)currentRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.set(key.getBytes(), SerializeUtil.serialize(object));
    }

    /**
     * 获取反序列化存储对象.
     * @param key
     */
    public Object getObject(String key) {
        Jedis jedis = (Jedis)currentRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        byte[] bytes = jedis.get(key.getBytes());
        if (bytes == null) return null;
        return SerializeUtil.unSerialize(bytes);
    }
}
