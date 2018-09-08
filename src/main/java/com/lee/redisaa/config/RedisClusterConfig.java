package com.lee.redisaa.config;

import com.lee.redisaa.perpeties.RedisClusterConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Configuration
@EnableConfigurationProperties(RedisClusterConfigProperties.class)
public class RedisClusterConfig {

    @Autowired
    private RedisClusterConfigProperties redisClusterConfigProperties;

    @Bean
    public RedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory(
                new RedisClusterConfiguration(redisClusterConfigProperties.getNodes()));
        return factory;
    }

    @Bean
    public RedisClusterConnection redisClusterConnection(RedisConnectionFactory connectionFactory) {
        return connectionFactory.getClusterConnection();
    }


}