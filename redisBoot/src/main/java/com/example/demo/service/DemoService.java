/**
 * 
 */
package com.example.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.example.demo.domian.User;
import com.example.demo.utils.JsonUtils;

/**
 * @author xuminzhe 2017年8月9日 上午10:46:36
 *
 */
@Service
public class DemoService {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private RedisTemplate<String, User> redisUserTemplate;

	public void setKey(Map<String, Map<?, ?>> map) {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		for (Map.Entry<String, Map<?, ?>> entry : map.entrySet()) {
			String jsonStringFromMap = JsonUtils.getJsonStringFromMap(entry.getValue());
			opsForValue.set(entry.getKey(), jsonStringFromMap);
		}
	}

	public void setKeyMap(Map<String, Map<?, ?>> map) {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		for (Map.Entry<String, Map<?, ?>> entry : map.entrySet()) {
			String jsonStringFromMap = JsonUtils.getJsonStringFromMap(entry.getValue());
			opsForValue.set(entry.getKey(), jsonStringFromMap);
		}
	}

	public void getKey(String boxUid) {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();

		if (redisTemplate.hasKey(boxUid)) {
			Object object = opsForValue.get(boxUid);
			System.out.println(object);
		}

	}

	public void setUser(User user) {
		ValueOperations<String, User> opsForValue = redisUserTemplate.opsForValue();
		opsForValue.set(user.getUsername(), user);
		Integer age = opsForValue.get(user.getUsername()).getAge();
		String username = opsForValue.get(user.getUsername()).getUsername();
		System.out.println(age + " " + username);
	}

}
