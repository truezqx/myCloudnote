package cn.zqx.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zqx.entity.User;
import cn.zqx.service.NameException;
import cn.zqx.service.PasswordException;
import cn.zqx.service.UserService;
import cn.zqx.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String name,String password){
		try{
			User user=userService.Login(name, password);
			return new JsonResult(user);
		}catch (NameException e){
			e.printStackTrace();
			return new JsonResult(2,e);
		}catch (PasswordException e){
			e.printStackTrace();
			return new JsonResult(3,e);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(e);
		}
		
	}
	@RequestMapping("/regist.do")
	@ResponseBody
	public Object regist(String name,String password,String nick){
		try{
			User user = userService.regist(name, password, nick);
			return new JsonResult(user);
		}catch (NameException e){
			e.printStackTrace();
			return new JsonResult(2,e);
		}catch (PasswordException e){
			e.printStackTrace();
			return new JsonResult(3,e);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(e);
		}
		
	}
	@RequestMapping("/checkName.do")
	@ResponseBody
	public Object checkName(String name){
		User user = userService.checkName(name);
		return new JsonResult(user);
	}
		

}
