package com.fx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.mapper.UserInfoMapper;
import com.fx.pojo.UserInfo;
@Service
public class UserInfoServiceImp implements UserInfoService{
	@Autowired(required=true)
	private UserInfoMapper userInfoMapper;


	@Override
	public UserInfo selectUserLoginInvalid(String userId, String password) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectUserLoginValidate(userId,password);
	}
	
	

}
