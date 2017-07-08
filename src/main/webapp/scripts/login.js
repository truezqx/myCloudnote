
$(function(){
		//登录事件
		$("#login").click(checklogin);
		//注册事件
		$("#regist_button").click(registUser);
	});


//登录处理
function checklogin(){
			$("#count_span").html("");
			$("#password_span").html("");
			//获取参数
			var name = $("#count").val().trim();
			var password = $("#password").val().trim();
			var ok = true;
			//检测参数格式
			if(name==""){
				$("#count_span").html("用户名不能为空");
				ok=false;
			}
			if(password==""){
				$("#password_span").html("密码不能为空");
				ok=false;
			}
			//发送AJAX请求
			if(ok){//通过检测
				$.ajax({
					"url":path+"/user/login.do",
					"type":"post",
					"data":{"name":name,"password":password},
					"dataType":"json",
					"success":function(result){
						if(result.state==0){
							var user = result.data;
							addCookie("userId",user.cn_user_id,2);
							addCookie("username",user.cn_user_name,2);
							window.location.href="edit.html"
						}else if(result.state==2){
							$("#count_span").html(result.message);
						}else if(result.state==3){
							$("#password_span").html(result.message);
						}
					},
					"error":function(){
						alert("服务器异常，登录失败");
					}
				});
			}
		}

//z注册处理
function registUser(){
	//清空提示信息
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	//获得提交参数
	var name=$("#regist_username").val().trim();
	var password=$("#regist_password").val().trim();
	var final_password=$("#final_password").val().trim();
	var nickname=$("#nickname").val().trim();
	var ok=true;
	//检测用户名
	if(name==""){
		ok = false;
		$("#warning_1").show();
		$("#warning_1 span").html("用户名为空");
	}
	//检测密码
	if(password==""){
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("密码不能为空");
	}else if(password.length<6){
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("密码不能小于6位");
	}
	if(final_password!=password){
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("密码不一致");
	}
	if(ok){
		$.ajax({
			"url":path+"/user/regist.do",
			"type":"post",
			"data":{"name":name,"password":password,"nickname":nickname},
			"dataType":"json",
			"success":function(result){
				//成功转向登录页面
				if(result.state==0){
					alert("注册成功")
					$("#back").click();
				}else if(result.state==2){
					$("#warning_1 span").html(result.message);
					$("#warning_1").show();
				}else if(result.state==3){
					$("#warning_2 span").html(result.message);
					$("#warning_2").show();
				}else{
					alert("服务器异常，注册失败");
				}
			},
			"error":function(){
				alert("系统异常，注册失败")
			}
		});
	}
}


//检测用户是否存在
function checkName(){
	var name =$("#regist_username").val().trim();
	$("#warning_1 span").html("");
	$.ajax({
		"url":path+"/user/checkName.do",
		"type":"post",
		"data":{"name":name},
		"dataType":"json",
		"success":function(result){
			if(result.data!=null){
				$("#warning_1").show();
				$("#warning_1 span").html("用户名已存在");
			}
		}
			
	});
}

