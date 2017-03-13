package com.atian.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xutiantian on 2017/3/10.
 */
@Configuration
@EnableConfigurationProperties({RedisShardProperties.class})
@PropertySource("classpath:jedis.properties")
@ConditionalOnMissingClass
public class RedisConfiguration {

    @Autowired
    private RedisShardProperties redisShardProperties;

    @Bean(name = "jedisPoolConfig")
    @ConfigurationProperties(prefix = "redis.pool")
    public JedisPoolConfig genJedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(name = "jedisShardInfo")
    @ConfigurationProperties(prefix = "redis.shard")
    public JedisShardInfo genJedisShardInfo() {
        JedisShardInfo jedisShardInfo = new JedisShardInfo(redisShardProperties.getHost(),
                redisShardProperties.getPort(), redisShardProperties.getTimeout());
        jedisShardInfo.setPassword(redisShardProperties.getPassword());
        return jedisShardInfo;
    }

    @Bean
    public ShardedJedisPool genShardedJedisPool(@Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig,
                                                JedisShardInfo jedisShardInfo) {
        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(jedisShardInfo);
        return new ShardedJedisPool(jedisPoolConfig, shards);
    }

}
