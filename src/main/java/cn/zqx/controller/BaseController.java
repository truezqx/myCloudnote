package cn.zqx.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zqx.util.JsonResult;

public abstract class BaseController {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult exceptionHandle(Exception e) {
		//参数e就是被捕获到的异常对象
		e.printStackTrace();
		return new JsonResult(e);
	}

}
