package com.fx.util;

public class reqularTest {
	public static void main(String[] args) {
		System.out.println(regular.isEmail("11@qq.com"));
		System.out.println(regular.isEmail("1qq.com"));
		System.out.println(regular.isMobile("15625126226"));
		System.out.println(regular.isMobile("11111111111"));
	}
}
