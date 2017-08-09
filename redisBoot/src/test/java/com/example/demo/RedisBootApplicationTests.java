package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringApplicationConfiguration(RedisBootApplication.class)
public class RedisBootApplicationTests {

	// @Autowired
	// private StringRedisTemplate stringRedisTemplate;
	//
	// @Test
	// public void test() throws Exception {
	// // 保存字符串
	// stringRedisTemplate.opsForValue().set("bbb", "111");
	// System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
	// Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	// }

	@Test
	public void contextLoads() {
	}

}
