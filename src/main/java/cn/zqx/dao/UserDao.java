package cn.zqx.dao;

import cn.zqx.entity.User;

public interface UserDao {
	public User findByName(String name);
	public void addUser(User user);
	public User findById(String userId);
}
