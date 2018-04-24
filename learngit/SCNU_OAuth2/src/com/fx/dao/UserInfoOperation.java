package com.fx.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.alibaba.fastjson.JSON;
import com.fx.mapper.ClientInformationMapper;
import com.fx.mapper.UserInfoMapper;
import com.fx.pojo.ClientInformation;
import com.fx.pojo.UserInfo;

public class UserInfoOperation {


	private static ApplicationContext context;

	// 获取工厂
	public static void getFactory() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:spring_config/applicationContext-dao.xml");
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
		
	//查询操作（用户基本信息）
	public static UserInfo selectUserInfo(String userId) throws Exception {
		getFactory();
		// 获取Mapper
		UserInfoMapper blogMapper = context.getBean(UserInfoMapper.class);
		UserInfo infor = blogMapper.selectUserInfo(userId);
		System.out.println(infor);
		return infor;

	}


	
	//插入操作（用户基本信息）
	public static boolean insertUserInfo(UserInfo info) throws Exception {
		getFactory();
		// 获取Mapper
		UserInfoMapper blogMapper = context.getBean(UserInfoMapper.class);
		boolean infor = blogMapper.insertUserInfo(info);
		System.out.println(infor);
		return infor;

	}
	
	
	
	//更新操作
	
	
	
	
	
	
	//删除操作
	

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		//查询返回学生信息对象
		selectUserInfo("123");

		//插入学生信息
		UserInfo info =new UserInfo();
		info.setUserId("1234");
		info.setUserName("1234");
		info.setPassword("1234");
		info.setUser_dept1("1234");
		info.setUser_dept2("12345");
		info.setUser_kind("12345");
		info.setEmail("11111");
		info.setEmail_flag("111");
		info.setPhone("1111");
	//	info.setOthers("kkkk");
		
		insertUserInfo(info);
		
		
	}
}
