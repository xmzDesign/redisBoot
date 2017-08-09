/**
 * 
 */
package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domian.User;
import com.example.demo.response.ResponseVo;
import com.example.demo.service.DemoService;

/**
 * @author xuminzhe 2017年8月9日 上午10:45:40
 *
 */
@RestController
@RequestMapping(value = "demo", produces = "application/json")
public class DemoController {
	@Autowired
	private DemoService demoService;

	@RequestMapping(value = "set")
	public ResponseVo setKey() {
		ResponseVo responseVo = new ResponseVo();
		try {
			Map<String, Map<?, ?>> map = new HashMap<String, Map<?, ?>>();
			Map<String, String> map2 = new HashMap<>();
			map2.put("value", "2");
			map.put("boxUid", map2);
			demoService.setKey(map);
			responseVo.setInfo("操作成功");
			responseVo.setData(map);
		} catch (Exception e) {
			responseVo.setInfo("操作失败");
			responseVo.setData(e.getMessage());
		}

		return responseVo;
	}

	@RequestMapping(value = "setUser")
	public ResponseVo setUser(User user) {
		ResponseVo responseVo = new ResponseVo();

		demoService.setUser(user);
		responseVo.setInfo("操作成功");

		return responseVo;
	}

	@RequestMapping(value = "get")
	public ResponseVo getKey(@RequestParam(value = "boxUid", required = true) String BoxUid) {
		ResponseVo responseVo = new ResponseVo();
		try {
			demoService.getKey(BoxUid);
			responseVo.setInfo("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseVo.setInfo("操作成功");
		}

		return responseVo;

	}

}
