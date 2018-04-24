package com.fx.mapper;

import org.apache.ibatis.annotations.Param;

import com.fx.pojo.ClientInformation;


public interface ClientInformationMapper {

/**
 * 
*		@Method: select 
*		@Description: 
*		@param  
*		@return void
*		@author FollowFire
*		@date 2018年4月1日 上午10:09:31
 */
	//查询客户端是否存在
	public ClientInformation selectClientExist(@Param("client_id")String client_id,@Param("redirect_url")String redirect_url);

	//查询客户端帐号密码是否正确
	public ClientInformation selectClientValidate(@Param("client_id")String client_id);

	public boolean insertClient(ClientInformation client);
	public boolean updateClient(@Param("email_flag")String email_flag,@Param("client_id")String client_id);
	
}
