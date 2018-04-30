package com.fx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.mapper.ClientInformationMapper;
import com.fx.pojo.ClientInformation;
@Service
public class ClientInfomationServiceImp implements ClientInfomationService{
	@Autowired
	private ClientInformationMapper clientInformationMapper;


	@Override
	public ClientInformation selectClient(String client_id,String redirect_url) {
		// TODO Auto-generated method stub
		return clientInformationMapper.selectClientExist(client_id,redirect_url);
	}
	
	

}
