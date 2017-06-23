package jp.smi.spring.mapper;

import jp.smi.spring.entity.User;

public interface UserMapper {
	User getUser(String login_id, String password);
}
