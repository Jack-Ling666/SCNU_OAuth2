package com.wxy.code;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CodeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String, Object> payload = new HashMap<String, Object>();
		Date date = new Date();
		payload.put("uid", "admin");// 用户ID
		//payload.put("iat", date.getTime());// 生成时间
	//	payload.put("ext", date.getTime() + 1000 * 60 * 60);// 过期时间1小时
		String token = Code.createToken(payload);
		System.out.println(token);
		
		
		
	}

}
