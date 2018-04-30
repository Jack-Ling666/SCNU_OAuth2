package com.fx.util;
import java.util.List;
import com.alibaba.fastjson.JSON;
public class MyJson {

	
	// 列表转为json
	public static <T> String toJson(List<T> list) throws Exception {

		// fastjson-1.2.7.jar
		String json = JSON.toJSONString(list, true).toString();
		System.out.println(json);
		return json;
	}
	
	
	public static void main(String[] args) {
		


		
		
	}

}
