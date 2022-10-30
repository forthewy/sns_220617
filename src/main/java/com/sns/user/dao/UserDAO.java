package com.sns.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserDAO {

	public int existloginId(String loginId);
	
	public void insertUser(
			@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("name") String name,
			@Param("email") String email);
	
	public User getUserByLoginIdAndPassword(
			@Param("loginId") String loginId, 
			@Param("password") String password);
	
	public User selectUserByLoginId(String loginId);
	
	public User selectUserById(int id);
	
	public List<User> selectUserListBysearchLoginId(String searchLoginId);
	
	public int updateUserByLoginId(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("name") String name,
			@Param("email") String email,
			@Param("profileImgPath") String profileImgPath);
	
}
