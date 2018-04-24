package com.fx.service;

import com.fx.pojo.UserInfo;

public interface UserInfoService {
/**
 * 
*		@Method: selectClient 
*		@Description: 
*		@param  
*		@return ClientInformation
*		@author FollowFire
*		@date 2018年4月1日 下午2:40:52
 */
	public UserInfo selectUserLoginInvalid(String userId,String password);


}
