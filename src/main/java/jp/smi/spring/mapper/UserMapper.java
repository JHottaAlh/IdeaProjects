package jp.smi.spring.mapper;

import org.apache.ibatis.annotations.Param;

import jp.smi.spring.entity.User;

public interface UserMapper {
	//参考:http://qiita.com/n_slender/items/25bb6b4661c0a7f0427e
	User getUser(@Param("login_id") String login_id, @Param("password") String password);
}
