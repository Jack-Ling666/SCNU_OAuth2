package com.wxy.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.minidev.json.JSONObject;

public class JwtTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String, Object> payload = new HashMap<String, Object>();
		Date date = new Date();
		payload.put("uid", "admin");// 用户ID
		payload.put("iat", date.getTime());// 生成时间
		payload.put("ext", date.getTime() + 1000 * 60 * 60);// 过期时间1小时
		String token = Jwt.createToken(payload);
		System.out.println(token);
		
		System.out.println("开始校验token");
		// 从请求头中获取token

		System.out.println(token);
		Map<String, Object> resultMap = Jwt.validToken("sssssss");

		TokenState state = TokenState.getTokenState((String) resultMap.get("state"));
		switch (state) {
		case VALID:
			// 取出payload中数据,放入到request作用域中
			System.out.println(resultMap.get("data"));
			// 放行
		
			break;
		case EXPIRED:
		case INVALID:
			System.out.println("无效token");
			// token过期或者无效，则输出错误信息返回给ajax
			JSONObject outputMSg = new JSONObject();
			outputMSg.put("success", false);
			outputMSg.put("msg", "您的token不合法或者过期了，请重新登陆");
			//output(outputMSg.toJSONString(), response);
			break;
		}
		
	}

}
