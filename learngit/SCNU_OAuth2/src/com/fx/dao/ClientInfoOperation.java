package com.fx.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.alibaba.fastjson.JSON;
import com.fx.mapper.ClientInformationMapper;
import com.fx.mapper.UserInfoMapper;
import com.fx.pojo.ClientInformation;
import com.fx.pojo.UserInfo;

public class ClientInfoOperation {


	private static ApplicationContext context;

	// 获取工厂
	public static void getFactory() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:spring_config/applicationContext-dao.xml");
	}


	
	//插入操作（客服端基本信息）
	public static boolean insertClient(ClientInformation info) throws Exception {
		getFactory();
		// 获取Mapper
		ClientInformationMapper blogMapper = context.getBean(ClientInformationMapper.class);
		boolean infor = blogMapper.insertClient(info);
		System.out.println(infor);
		return infor;

	}
	
	//更新操作
	
	public static boolean updateClient(String email_flag,String client_id) throws Exception {
		getFactory();
		// 获取Mapper
		ClientInformationMapper blogMapper = context.getBean(ClientInformationMapper.class);
		boolean infor = blogMapper.updateClient(email_flag,client_id);
		System.out.println(infor);
		return infor;

	}
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));

		UUID client_id = UUID.randomUUID();
		System.out.println(client_id.toString());
		//插入客户端信息
		ClientInformation info =new ClientInformation();
		info.setClient_id(client_id.toString());
		info.setClient_name("ss");
		info.setClient_secret("ss");
		info.setEmail("ss");
		info.setAccess_token_validity("ss");
		info.setApplicant_name("");
		info.setEmail_flag("0");

		//insertClient(info);
		
		
	//	updateClient("1","c212959d-2410-42ce-923a-f563430cd1b9");
		String acticve ="active";
		System.out.println(acticve.equals("active"));
	
		
	}
}
