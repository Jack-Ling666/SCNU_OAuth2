package com.fx.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailSend {
	/**
	 * 
	 * @param client_id
	 * @param token
	 * @param mailaddress
	 * @param classPath
	 * @return 
	 */
	public static  boolean Mail(String client_id,String token,String mailaddress,String url) {
		try {
			
			 Properties properties = new Properties();
				
			  properties.put("mail.transport.protocol", "smtp");// 连接协议        
			
			 properties.put("mail.smtp.host", "smtp.vip.olivemail.net");// 主机名      
			  
 
			
			  properties.put("mail.smtp.port", 465);// 端口号        
			
			  properties.put("mail.smtp.auth", "true");   
			  
			
			  properties.put("mail.smtp.ssl.enable", "true");//设置是否使用ssl安全连接  ---一般都使用        
			
			  properties.put("mail.debug", "true");//设置是否显示debug信息  true 会在控制台显示相关信息        
			
//			  String classPath1 = MailSend.class.getResource("/").getPath();
//			  System.out.println("cp:"+classPath1);
//			  
//			  
//			  
//			  System.setProperty("javax.net.ssl.trustStore", classPath1+"\\jssecacerts");
			  
			  
			  
			//得到回话对象        
			
			Session session = Session.getInstance(properties);        
			
			// 获取邮件对象        
			
			Message message = new MimeMessage(session);        
			
			//设置发件人邮箱地址       
			
			 message.setFrom(new InternetAddress("ssp@eswis.cn"));
			
			 //设置收件人地址        
			 message.setRecipients(
					 RecipientType.TO, 
					 new InternetAddress[] { new InternetAddress(mailaddress) }
					 );       
			
			 //设置邮件标题        
			
			message.setSubject("客户端激活");        
			
			//设置邮件内容 
			StringBuffer sb=new StringBuffer();
			
	        sb.append(url);  

	        sb.append(token); 
	         
			String text = "您好，您的客户端账号是："+client_id+"，请点击:"+sb+";激活邮箱";
			
			message.setText(text);       
			
			 //得到邮差对象        
			
			Transport transport = session.getTransport();        
			
			//连接自己的邮箱账户    
			transport.connect("ssp@eswis.cn", "es39310307");//密码为刚才得到的授权码        
			
			//发送邮件        
			transport.sendMessage(message, message.getAllRecipients());   
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
