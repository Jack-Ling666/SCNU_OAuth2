package com.fx.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.fx.dao.LoginValidateOperation;
import com.fx.dao.UserInfoOperation;
import com.fx.pojo.UserInfo;
import com.wxy.jwt.Jwt;
import com.wxy.jwt.TokenState;

@Controller
public class TokenController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	@ResponseBody
	public String token(Model model, HttpServletRequest request, HttpServletResponse response) {

		String client_id = request.getParameter("client_id");
		String md5key = request.getParameter("key");
		String code = request.getParameter("code");

		System.out.print("客户端:" + client_id + " 来请求token ");
		System.out.print(" 密码是:" + md5key);
		System.out.print(" code是:" + code);

		// 查表判断客户端帐号是否正确
		boolean clientStatus = false;
		try {
			//客户端帐号是否正确
			clientStatus = LoginValidateOperation.selectClientValidate(client_id,md5key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(clientStatus);
		String token = null;

		// 解析code,取出client_id,userId;

		if (clientStatus) {
		System.out.println("开始校验CODE");

		System.out.println(code);
		Map<String, Object> resultMap = Jwt.validToken(code);
		Map<String, String> param = new HashMap<String, String>();
		TokenState state = TokenState.getTokenState((String) resultMap.get("state"));
		switch (state) {
		case VALID:
			// 取出data部分
			String userId =null;
			String mycode =null;
			System.out.println(resultMap.get("data"));
			
			  for (String key : ((HashMap<String, Object>) resultMap.get("data")).keySet()) {  
				   System.out.println("key= "+ key + "  value= " + ((HashMap<String, Object>) resultMap.get("data")).get(key));  
				 
			      if(key.equals("userId")){		    	  
			    	  userId=((HashMap<String, Object>) resultMap.get("data")).get(key).toString();
			      }			      
			      if(key.equals("code")){
			    	  
			    	  mycode=((HashMap<String, Object>) resultMap.get("data")).get(key).toString();
			      }
			  }
			
			
			// 查询用户信息
			UserInfo userInfo = new UserInfo();
			try {
				
				if(mycode.equals("FX6419")){
				if(userId!=null){
				userInfo = UserInfoOperation.selectUserInfo(userId);
				}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			// 返回token

			Map<String, Object> payload = new HashMap<String, Object>();
			Date date = new Date();
			payload.put("client_id", client_id);// 客户端ID
			payload.put("userId", userId);// 用户ID
			payload.put("token", "Surprise_mo_fu");// 暗语
			payload.put("iat", date.getTime());// 生成时间
			payload.put("ext", date.getTime() + 1000 * 60 * 60);// 过期时间60分钟

			token = Jwt.createToken(payload);
			System.out.println("服务器生成的token是" + token);
			System.out.println("开始返回token和用户信息");


			param.put("token", token);
			param.put("userId", userInfo.getUserId());
			param.put("userName", userInfo.getUserName());
			param.put("user_dept1", userInfo.getUser_dept1());
			param.put("user_dept2", userInfo.getUser_dept2());
			param.put("user_kind", userInfo.getUser_kind());
			param.put("email", userInfo.getEmail());
			param.put("email_flag", userInfo.getEmail_flag());
			param.put("phone", userInfo.getPhone());
			param.put("userId", userInfo.getOthers());
			break;
		case EXPIRED:
			System.out.println("expired");
			param.put("token", "expired");
			break;
		case INVALID:
			System.out.println("invalid");
			param.put("token", "invalid");
			break;
		}
		
	
		
		
		// 判断客户端状态，登录成功就返回token和用户信息
		

		


			String json = JSON.toJSONString(param, true).toString();
			return json;

		}
		return token;
	}

	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/userinfo", method = RequestMethod.POST)
	@ResponseBody
	public String test(Model model, HttpServletRequest request, HttpServletResponse response) {

		String token = request.getParameter("token");
		System.out.println("开始校验token");
		// 从请求头中获取token
		System.out.println(token);
		Map<String, Object> resultMap = Jwt.validToken(token);
		Map<String, String> param = new HashMap<String, String>();
		TokenState state = TokenState.getTokenState((String) resultMap.get("state"));
		switch (state) {
		case VALID:
			// 取出data部分
			String userId =null;
			String mytoken =null;
			System.out.println(resultMap.get("data"));
			
			  for (String key : ((HashMap<String, Object>) resultMap.get("data")).keySet()) {  
				   System.out.println("key= "+ key + "  value= " + ((HashMap<String, Object>) resultMap.get("data")).get(key));  
				 
			      if(key.equals("userId")){
			    	  
			    	  userId=((HashMap<String, Object>) resultMap.get("data")).get(key).toString();
			      }
			      
			      if(key.equals("token")){
			    	  
			    	  mytoken=((HashMap<String, Object>) resultMap.get("data")).get(key).toString();
			      }
			  }
			
			  if(mytoken==null){
		  
				  param.put("token", "invalid");
				  
			  }
			  
			  
			
			// 查询用户信息
			UserInfo userInfo = new UserInfo();
			try {
				if(mytoken!=null){
				if(mytoken.equals("Surprise_mo_fu")){
				if(userId!=null){
				userInfo = UserInfoOperation.selectUserInfo(userId);
				}
				}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			param.put("userId", userInfo.getUserId());
			param.put("userName", userInfo.getUserName());
			param.put("user_dept1", userInfo.getUser_dept1());
			param.put("user_dept2", userInfo.getUser_dept2());
			param.put("user_kind", userInfo.getUser_kind());
			param.put("email", userInfo.getEmail());
			param.put("email_flag", userInfo.getEmail_flag());
			param.put("phone", userInfo.getPhone());
			param.put("userId", userInfo.getOthers());
			break;
		case EXPIRED:
			System.out.println("expired");
			param.put("token", "expired");
			break;
		case INVALID:
			System.out.println("invalid");
			param.put("token", "invalid");
			break;
		}
		String json = JSON.toJSONString(param, true).toString();
		return json;

	}

}
