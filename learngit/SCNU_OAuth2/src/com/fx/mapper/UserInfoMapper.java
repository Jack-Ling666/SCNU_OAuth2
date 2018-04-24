package com.fx.mapper;

import org.apache.ibatis.annotations.Param;

import com.fx.pojo.UserInfo;

public interface UserInfoMapper {

/**
 * 
*		@Method: 
*		@Description: 
*		@param  多种参数/对象
*		@return List<Test>
*		@author FollowFire
*		@date 2018年3月24日 上午11:03:03
 */
	

	//用户登录验证接口
	public UserInfo selectUserLoginValidate(@Param("userId")String userId,@Param("password") String password);
	
	//查询用户基本信息，返回对象
	public UserInfo selectUserInfo(String userId);
	
	//插入用户信息
	public boolean insertUserInfo(UserInfo info);
	
	
	//更新用户信息
	public boolean updateUserInfo(UserInfo info);
	
	
	
	
	
	
}
