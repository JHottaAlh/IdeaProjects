package jp.smi.spring.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.smi.spring.dto.UserDto;
import jp.smi.spring.entity.User;
import jp.smi.spring.mapper.UserMapper;

@Service
public class LoginService {
	
	@Autowired
	private UserMapper userMapper;
	
	public UserDto getUser(String login_id, String password){
		//UserDto型のインスタンスを作成
		UserDto dto = new UserDto();
		User entity = userMapper.getUser(login_id, password);
		BeanUtils.copyProperties(entity, dto);
		
		return dto;
	}
}
