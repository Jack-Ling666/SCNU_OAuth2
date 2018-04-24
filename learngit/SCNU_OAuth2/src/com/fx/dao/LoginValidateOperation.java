package com.fx.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.alibaba.fastjson.JSON;
import com.fx.mapper.ClientInformationMapper;
import com.fx.mapper.UserInfoMapper;
import com.fx.pojo.ClientInformation;
import com.fx.pojo.UserInfo;
import com.fx.util.MD5;

public class LoginValidateOperation {
	
	//由spring实例化工厂
	private static ApplicationContext context;

	// 获取工厂
	public static void getFactory() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:spring_config/applicationContext-dao.xml");
	}



	// 查询操作（客户端是否存在）
	public static boolean selectClientExist(String client_id, String redirect_url) throws Exception {
		getFactory();
		// 获取Mapper
		ClientInformationMapper blogMapper = context.getBean(ClientInformationMapper.class);
		ClientInformation info = blogMapper.selectClientExist(client_id, redirect_url);
		System.out.println(info);

		if (info != null) {
			if (info.getClient_id().equals(client_id) && info.getRedirect_url().equals(redirect_url)) {
				return true;
			}
		}
		return false;
	}

	// 查询操作（客户端帐号是否正确）
	public static boolean selectClientValidate(String client_id, String md5key) throws Exception {
		getFactory();
		// 获取Mapper
		ClientInformationMapper blogMapper = context.getBean(ClientInformationMapper.class);
		ClientInformation info = blogMapper.selectClientValidate(client_id);
		System.out.println(info);
		if (info != null) {
			
			String mdsecret=MD5.CreateKey(client_id, info.getClient_secret());
			System.out.println(mdsecret);
			if (info.getClient_id().equals(client_id) &&mdsecret.equals(md5key) ) {
				return true;
			}
		}
		return false;
	}
	
	
	// 查询操作（用户登录验证）
	public static boolean selectLogin(String userId, String password) throws Exception {
		getFactory();
		// 获取Mapper
		UserInfoMapper blogMapper = context.getBean(UserInfoMapper.class);
		UserInfo info = blogMapper.selectUserLoginValidate(userId, password);
		System.out.println(info);
		
		if (info != null) {
			if (info.getUserId().equals(userId) && info.getPassword().equals(password)) {
				return true;
			}
		}
		return false;

	}



	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		// selectTset();

	//	UUID id = UUID.randomUUID();
	//	System.out.println(id.toString());
	//	boolean clientInformation = selectClientExist("89a73304-45f5-49f8-9090-5063fd8924fd", "https://www.baidu.com/");
	//	System.out.println(clientInformation);
	//	selectClientExist("89a73304-45f5-49f8-9090-5063fd8924fd", "https://www.baidu.com/");
		//selectLogin("123", "123");
	//	selectClientValidate("89a73304-45f5-49f8-9090-5063fd8924fd","7bef0d67-66dc-41d4-a6c5-faeee63fe966");
		// selectClient("2");
		
		String mdsecret=MD5.CreateKey("807342a4-8d5d-41b4-bd7f-8a63355e1850", "111111");
		
		System.out.println(mdsecret);
		
		
		System.out.println(selectClientValidate("807342a4-8d5d-41b4-bd7f-8a63355e1850", mdsecret));
		
	}

}
