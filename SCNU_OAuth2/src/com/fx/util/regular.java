package com.fx.util;

import java.util.regex.Pattern;
public class regular {
	/**
     * ������ʽ����֤�û���
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";
 
    /**
     * ������ʽ����֤����
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";
 
    /**
     * ������ʽ����֤�ֻ���
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
 
    /**
     * ������ʽ����֤����
     */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
 
    /**
     * ������ʽ����֤����
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
 
    /**
     * ������ʽ����֤���֤
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
 
    /**
     * ������ʽ����֤URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
 
    /**
     * ������ʽ����֤IP��ַ
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
 
    /**
     * У���û���
     * 
     * @param username
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }
 
    /**
     * У������
     * 
     * @param password
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
 
    /**
     * У���ֻ���
     * 
     * @param mobile
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
 
    /**
     * У������
     * 
     * @param email
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
 
    /**
     * У�麺��
     * 
     * @param chinese
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }
 
    /**
     * У�����֤
     * 
     * @param idCard
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }
 
    /**
     * У��URL
     * 
     * @param url
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }
 
    /**
     * У��IP��ַ
     * 
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
}
