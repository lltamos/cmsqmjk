package com.quanmin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by llsmp on 2017/7/21.
 */
@Component
public class RedisUtils {
    /**
     * 日志记录
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RedisTemplate redisTemplate;


    @Resource
    private JedisPool jedisPool;


    /**
     * 缓存value操作
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    protected boolean cacheValue(String k, String v, long time) {
        String key =  k;
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存value操作
     *
     * @param k
     * @param v
     * @return
     */
    protected boolean cacheValue(String k, String v) {
        return cacheValue(k, v, -1);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k
     * @return
     */
    protected boolean containsValueKey(String k) {
        return containsKey( k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k
     * @return
     */
    protected boolean containsSetKey(String k) {
        return containsKey( k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k
     * @return
     */
    protected boolean containsListKey(String k) {
        return containsKey( k);
    }

    protected boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            logger.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 获取缓存
     *
     * @param k
     * @return
     */
    protected String getValue(String k) {

        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return valueOps.get( k);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" +  k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 移除缓存
     *
     * @param k
     * @return
     */
    protected boolean removeValue(String k) {
        return remove( k);
    }

    protected boolean removeSet(String k) {
        return remove( k);
    }

    protected boolean removeList(String k) {
        return remove( k);
    }

    /**
     * 移除缓存
     *
     * @param key
     * @return
     */
    protected boolean remove(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 缓存set操作
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    protected boolean cacheSet(String k, String v, long time) {
        String key =  k;
        try {
            SetOperations<String, String> valueOps = redisTemplate.opsForSet();
            valueOps.add(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k
     * @param v
     * @return
     */
    protected boolean cacheSet(String k, String v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 缓存set
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    protected boolean cacheSet(String k, Set<String> v, long time) {
        String key =  k;
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            setOps.add(key, v.toArray(new String[v.size()]));
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k
     * @param v
     * @return
     */
    protected boolean cacheSet(String k, Set<String> v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 获取缓存set数据
     *
     * @param k
     * @return
     */
    protected Set<String> getSet(String k) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            return setOps.members( k);
        } catch (Throwable t) {
            logger.error("获取set缓存失败key[" +  k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * list缓存
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    protected boolean cacheList(String k, String v, long time) {
        String key =  k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPush(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k
     * @param v
     * @return
     */
    protected boolean cacheList(String k, String v) {
        return cacheList(k, v, -1);
    }

    /**
     * 缓存list
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    protected boolean cacheList(String k, List<String> v, long time) {
        String key = k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            long l = listOps.rightPushAll(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k
     * @param v
     * @return
     */
    protected boolean cacheList(String k, List<String> v) {
        return cacheList(k, v, -1);
    }

    /**
     * 获取list缓存
     *
     * @param k
     * @param start
     * @param end
     * @return
     */
    protected List<String> getList(String k, long start, long end) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.range(k, start, end);
        } catch (Throwable t) {
            logger.error("获取list缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param k
     * @return
     */
    protected long getListSize(String k) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.size(k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param listOps
     * @param k
     * @return
     */
    protected long getListSize(ListOperations<String, String> listOps, String k) {
        try {
            return listOps.size(k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 移除list缓存
     *
     * @param k
     * @return
     */
    protected boolean removeOneOfList(String k) {
        String key = k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPop(k);
            return true;
        } catch (Throwable t) {
            logger.error("移除list缓存失败key[" + k + ", error[" + t + "]");
        }
        return false;


    }

    /**
     * Redis Sadd 命令将一个或多个成员元素加入到集合中，已经存在于集合的成员元素将被忽略。
     * 假如集合 key 不存在，则创建一个只包含添加的元素作成员的集合。
     * 当集合 key 不是集合类型时，返回一个错误。
     * 注意：在Redis2.4版本以前， SADD 只接受单个成员值。
     *
     * @param key
     * @param member
     * @return
     */
    public Long sadd(String key, String... member) {
        Jedis jedis = jedisPool.getResource();
        try {
            Long sadd = jedis.sadd(key, member);
            jedis.close();
            return sadd;
        } catch (Throwable t) {
            logger.error("增加key[" + key + ", error[" + member + "]");
        }
        return 0L;
    }


    /**
     * Redis Smembers 命令返回集合中的所有的成员。 不存在的集合 key 被视为空集合
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            Set<String> stringSet = jedis.smembers(key);
            jedis.close();
            return stringSet;
        } catch (Throwable t) {
            logger.error("查询key[" + key + "]");
        }
        return null;
    }


    public Long incrs(String key){
        Jedis jedis = jedisPool.getResource();

        try {
            Long aLong = jedis.incr( key );
            jedis.close();
            return aLong;
        } catch (Throwable t) {
            logger.error("查询key[" + key + "]");
        }

        return null;
    }


}