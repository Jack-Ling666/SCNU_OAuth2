package com.fx.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

import com.fx.dao.LoginValidateOperation;
import com.fx.util.InvalidString;
import com.wxy.code.Code;

@Controller
public class CodeController {

	@RequestMapping(value = "/client/openapi", method = RequestMethod.GET)
	public String Code(Model model, HttpServletRequest request, HttpServletResponse response) throws UnknownHostException {

		String client_id = request.getParameter("client_id");
		String redirect_url = request.getParameter("redirect_url");
		System.out.println(client_id);
		System.out.println(redirect_url);
		InetAddress ip = InetAddress.getLocalHost();
		String myip = ip.getHostAddress();
		String list = "http://"+myip+":"+request.getLocalPort()+"/SCNU_OAuth2/login/code?client_id=" + client_id + "&" + "redirect_url="
				+ redirect_url;
		model.addAttribute("list", list);

		return "login";

	}

	@RequestMapping(value = "/login/code", method = RequestMethod.POST)
	@ResponseBody
	public void login(Model model, HttpServletRequest request, HttpServletResponse response) {

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String client_id = request.getParameter("client_id");
		String redirect_url = request.getParameter("redirect_url");

		System.out.println("userId:"+userId);
		System.out.println("password:"+password);
		
		
		System.out.println("client_id:"+client_id);
		System.out.println("redirect_url:"+redirect_url);

		//System.out.println("批量null："+InvalidString.isNotEmptyBatch(client_id, password, client_id, redirect_url));
		
		
		if (InvalidString.isNotEmptyBatch(client_id, password, client_id, redirect_url)) {
			
			boolean clientInformation = false;
			try {
				//查询客户端是否存在
				clientInformation = LoginValidateOperation.selectClientExist(client_id, redirect_url);
			} catch (Exception e2) {
				
				e2.printStackTrace();
			}
			System.out.println("clientInformation:"+clientInformation);
			boolean loginStatus = false;
			try {
				//判断用户是否登录成功
				loginStatus = LoginValidateOperation.selectLogin(userId, password);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			System.out.println("loginStatus:"+loginStatus);
			if (clientInformation) {//客户端存在
				if (loginStatus) {//用户登录成功
										
					System.out.println("test");
					
					Map<String, Object> payload = new HashMap<String, Object>();
					Date date = new Date();
					payload.put("client_id", client_id);// 客户端ID
					payload.put("userId", userId);// 用户ID
					payload.put("code", "FX6419");// 暗语
					payload.put("iat", date.getTime());// 生成时间
					payload.put("ext", date.getTime() + 1000 * 60 *5);// 过期时间5分钟
					
					String code = Code.createToken(payload);
					System.out.println("服务器生成的code是"+code);
					
					try {
						System.out.println("重定向："+redirect_url + "?code=" + code);
						response.sendRedirect(redirect_url + "?code=" + code);
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}
		}
	}



}
