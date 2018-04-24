package com.fx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fx.dao.UserInfoOperation;
import com.fx.pojo.UserInfo;
import com.fx.util.InvalidString;

@Controller
public class UserInfoController {

	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String Code(Model model, HttpServletRequest request, HttpServletResponse response) {

		
		return "register";

	}


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String Register(UserInfo info, HttpServletRequest request, HttpServletResponse response) {

		boolean flag=false;
		try {
			if(InvalidString.isNotEmptyBatch(info.getUserId().toString(),info.getUserName().toString(),info.getPassword().toString()))
				flag=UserInfoOperation.insertUserInfo(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println(info);
		
		if(flag)
		return "success";
		
		return "error";
	

		
		
	}


}
