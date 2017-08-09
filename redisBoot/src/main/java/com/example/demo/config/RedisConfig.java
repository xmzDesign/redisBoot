/**
 * 
 */
package com.example.demo.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.demo.domian.User;
import com.example.demo.serializer.RedisObjectSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author xuminzhe 2017年8月9日 上午11:29:54
 *
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig extends CachingConfigurerSupport {
	private static Logger logger = Logger.getLogger(RedisConfig.class);

	@Autowired
	private Environment env;

	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		System.out.println(config.getMaxWaitMillis());
		return config;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	public JedisConnectionFactory getConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisPoolConfig config = getRedisConfig();
		factory.setPoolConfig(config);
		factory.setHostName(env.getProperty("spring.redis.host"));
		factory.setPort(Integer.parseInt(env.getProperty("spring.redis.port").trim()));
		factory.setDatabase(Integer.parseInt(env.getProperty("spring.redis.database").trim()));
		logger.info("JedisConnectionFactory bean init success.");
		return factory;
	}

	@Bean
	public RedisTemplate<String, ?> getRedisTemplate() {
		RedisTemplate<String, ?> template = new RedisTemplate();
		template.setConnectionFactory(getConnectionFactory());

		return template;
	}

	@Bean
	Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer(ObjectMapper objectMapper) {
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		return jackson2JsonRedisSerializer;
	}

	@Bean
	RedisTemplate<String, Object> objRedisTemplate(JedisConnectionFactory connectionFactory,
			Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(getConnectionFactory());
		redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		return redisTemplate;
	}

	@Bean
	public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, User> template = new RedisTemplate<String, User>();
		template.setConnectionFactory(getConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}

}
