package cn.zqx.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zqx.entity.User;
import cn.zqx.service.NameException;
import cn.zqx.service.PasswordException;
import cn.zqx.service.UserNotFoundException;
import cn.zqx.service.UserService;
import cn.zqx.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	private static Logger log=Logger.getLogger(UserController.class); 
	
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
	
	@RequestMapping("/checkPassword.do")
	@ResponseBody
	public JsonResult checkPassword(String userId,String lpassword){
		try{
			boolean success = userService.checkPassword(userId, lpassword);
			return new JsonResult(success);
		}catch(NameException e){
			e.printStackTrace();
			return new JsonResult(2,e);
		}catch(PasswordException e){
			e.printStackTrace();
			return new JsonResult(2,e);
		}catch(UserNotFoundException e){
			e.printStackTrace();
			return new JsonResult(2,e);
		}
		
	}
	@RequestMapping("/changePassword.do")
	@ResponseBody
	public JsonResult changePassword(String userId,String npassword){
		boolean success = userService.changePassword(userId, npassword);
		return new JsonResult(success);
	}
		

}
