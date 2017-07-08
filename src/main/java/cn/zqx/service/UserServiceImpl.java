package cn.zqx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zqx.dao.UserDao;
import cn.zqx.entity.User;
import cn.zqx.util.NoteUtil;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource(name="userDao")
	private UserDao userDao;

	public User Login(String name, String password) throws NameException, PasswordException {
		if(name==null||name.trim().isEmpty()){
			throw new NameException("用户名不能为空");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		User user = userDao.findByName(name);
		if(user==null){
			throw new NameException("用户名错误");
		}
		if(user.getCn_user_password().equals(NoteUtil.md5(password))){
			return user;
		}else{
			throw new PasswordException("密码错误");
		}
		
	}
	@Transactional
	public User regist(String name, String password, String nick) throws NameException, PasswordException {
		User user = userDao.findByName(name);
		if(user!=null){
			throw new NameException("用户名已存在");
		}
		if(name==null||name.trim().isEmpty()){
			throw new NameException("用户名不能为空");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		if(nick==null||nick.trim().isEmpty()){
			nick=name;
		}
		String id = NoteUtil.createId();
		String token = null;
		String md5_pwd = NoteUtil.md5(password);
		user = new User(id,name,md5_pwd,token,nick);
		userDao.addUser(user);
		return user;
	}

	public User checkName(String name) {
		User user = userDao.findByName(name);
		return user;
	}

}
