package com.fx.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.fx.dao.ClientInfoOperation;

import com.fx.jwt.Jwt;
import com.fx.jwt.TokenState;
import com.fx.pojo.ClientInformation;

import com.fx.util.MailSend;

import com.fx.util.regular;


@Controller
@RequestMapping(value = "/client", method = RequestMethod.GET)
public class SendEmailController {

	// 返回注册页面
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String Code(Model model, HttpServletRequest request, HttpServletResponse response) {


		
		return "clientregister";

	}
	
	@RequestMapping(value = "/returnregister", method = RequestMethod.GET)
	public String returnregister(Model model, HttpServletRequest request, HttpServletResponse response) {


		
		return "clientregister";

	}

	// 接收表单，发送邮箱激活码
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String Email(ClientInformation info, HttpServletRequest request, HttpServletResponse response) throws UnknownHostException {

		UUID client_id = UUID.randomUUID();
		System.out.println("client_id:"+client_id.toString());
		System.out.println("client_secret:"+info.getClient_secret());
		// 获取客户端信息

		// 初步存储到数据库 (生成id,flag)
		info.setClient_id(client_id.toString());
		info.setEmail_flag("0");
		
		System.out.println("info:"+info);
		boolean flag = false;
		String email = info.getEmail();
		System.out.println("email:"+email);
		System.out.println("isemail:"+regular.isEmail(email));
		
		if ( regular.isEmail(info.getEmail())) {

			try {
				flag = ClientInfoOperation.insertClient(info);
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		// 返回token，发送邮箱激活码

		System.out.println(flag);
		if (flag) {

			Map<String, Object> payload = new HashMap<String, Object>();
			Date date = new Date();
			payload.put("client_id", client_id.toString());// 客户端ID
			payload.put("redirect_url", info.getRedirect_url().toString());// 客户端ID
			payload.put("active", "active");// 激活
			payload.put("iat", date.getTime());// 生成时间
			payload.put("ext", date.getTime() + 1000 * 60 * 60);// 过期时间60分钟

			String token = Jwt.createToken(payload);
			System.out.println("服务器生成的token是" + token);
			System.out.println("开始返回token和用户信息");
			System.out.println("getemail:"+info.getEmail().toString());

			InetAddress ip = InetAddress.getLocalHost();
			String myip = ip.getHostAddress();
			
			boolean send_flag = MailSend.Mail(client_id.toString(), token, email,"http://"+myip+":"+request.getLocalPort()+"/SCNU_OAuth2/client/active?token=");
			System.out.println("client_id:"+client_id);
			System.out.println("token:"+token);
			System.out.println("email:"+email);
			if(send_flag) 
				return "clientsuccess";
			else 
				return "clienterror";
			
		}
		return "clienterror";

	}
	
	
	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	public String sendEmail(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		
		

		
		return null;
	}
	
	

	
	
	
	// 客户端激活接口
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String active(Model model, HttpServletRequest request, HttpServletResponse response) {

		
		String client_id=null;
		String redirect_url=null;
		String token = request.getParameter("token");
		System.out.println("开始校验token");
		// 从请求头中获取token
		System.out.println("token:"+token);
		Map<String, Object> resultMap = Jwt.validToken(token);
		TokenState state = TokenState.getTokenState((String) resultMap.get("state"));
		boolean flag = false;
		switch (state) {
		case VALID:
			// 取出data部分

			String active = null;
	
			System.out.println(resultMap.get("data"));

			for (String key : ((HashMap<String, Object>) resultMap.get("data")).keySet()) {
				System.out.println(
						"key= " + key + "  value= " + ((HashMap<String, Object>) resultMap.get("data")).get(key));

				if (key.equals("active")) {
					active = ((HashMap<String, Object>) resultMap.get("data")).get(key).toString();
				}
				
				if (key.equals("client_id")) {
					client_id = ((HashMap<String, Object>) resultMap.get("data")).get(key).toString();
				}
				
				
				if (key.equals("redirect_url")) {
					redirect_url = ((HashMap<String, Object>) resultMap.get("data")).get(key).toString();
				}
				
			}
			try {
				System.out.println(active);
				System.out.println(client_id);
					if (active.equals("active")) {
						flag=ClientInfoOperation.updateClient("1", client_id);
					}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case EXPIRED:
			return "error";
		case INVALID:
			return "error";
		}
		
		if(flag){
			return "redirect:/client/success?client_id="+client_id+"&redirect_url="+redirect_url;
		}
		
		return "error";
		
	}

	// 返回激活页面(获取登录链接)
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(Model model, HttpServletRequest request, HttpServletResponse response) throws UnknownHostException {

		
		String client_id = request.getParameter("client_id");
		String redirect_url = request.getParameter("redirect_url");
		System.out.println(client_id);
		System.out.println(redirect_url);
		InetAddress ip = InetAddress.getLocalHost();
		String myip = ip.getHostAddress();
		String list = "http://"+myip+":"+request.getLocalPort()+"/SCNU_OAuth2/client/openapi?client_id=" + client_id + "&" + "redirect_url="
				+ redirect_url;
		model.addAttribute("list", list);
		
		
		
		return "active";

	}
	
	
	
	
	
	
}
