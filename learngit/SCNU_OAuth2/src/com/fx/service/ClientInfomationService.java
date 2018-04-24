package com.fx.service;

import com.fx.pojo.ClientInformation;

public interface ClientInfomationService {
/**
 * 
*		@Method: selectClient 
*		@Description: 
*		@param  
*		@return ClientInformation
*		@author FollowFire
*		@date 2018年4月1日 下午2:40:52
 */
	public ClientInformation selectClient(String client_id,String redirect_url);


}
