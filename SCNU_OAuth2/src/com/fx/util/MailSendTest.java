package com.fx.util;

import java.io.File;

public class MailSendTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 //MailSend.Mail("111", "111", "1486385554@qq.com","http://localhost:8080/SCNU_OAuth2/active?token=");
		
		 
		 String classPath = MailSendTest.class.getResource("").getPath();
		 System.out.println(System.getProperty("user.dir"));
		 String aa=System.getProperty("user.dir");
		 System.out.println(aa);
		// char SEP = File.separatorChar;
		// System.out.println(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
		 
		 
	//  MailSend.Mail("11111", "11", "1486385554@qq.com");
	 MailSend.Mail("11", "11","1486385554@qq.com","11"); 
		 
		 
		 
		 
	}

}
