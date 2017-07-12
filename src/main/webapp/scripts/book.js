/*
 *封装笔记本相关处理 
 */
function loadUserBooks(){
	//获取请求参数
	var userId = getCookie("userId");
	//检查格式
	if(userId==null){
		window.location.href="log_in.html";
	}else{
		//发送ajax请求
		$.ajax({
			"url":path+"/book/loadbooks.do",
			"type":"post",
			"data":{"userId":userId},
			"dataType":"json",
			"success":function(result){
				if(result.state==0){
					//获取结果集
					var books = result.data;
					//循环生成列表元素
					for(var i=0;i<books.length;i++){
						 var bookId = books[i].cn_notebook_id;
						 var bookName = books[i].cn_notebook_name;
						 //构建页面
						 createBookLi(bookId,bookName);
					}
				}
			},
			"error":function(){
				alert("服务器异常，内容加载失败")
			}
		});
	}
	
}

function createBookLi(bookId,bookName){
	var sli = "";
	 sli+='<li class="online">';
	 sli+='  <a  >';
	 sli+='    <i class="fa fa-book" title="online" rel="tooltip-bottom">';
	 sli+='    </i>'+bookName;
	 sli+='     <button type="button" class="btn btn-default btn-xs btn_position btn_slide_down" id="delete_book"><i class="fa fa-times"></i></button>'
	 sli+='  </a>';
	 sli+='</li>';
	 //将bookId绑定到li元素上
	 var $li = $(sli);
	 $li.data("bookId",bookId);
	 //将li元素添加到ul列表中
	 $("#book_ul").prepend($li);
}

function addNotebook(){
	var userId = getCookie("userId");
	var bookName = $("#input_notebook").val().trim();
	var ok=true;
	if(userId==""){
		ok=false;
		window.location.href="log_in.html";
	}
	if(bookName==""){
		ok=false;
		closeWindow();
		alert("笔记本名不能为空");
	}
	if(ok){
		$.ajax({
			url:path+"/book/addbook.do",
			type:"post",
			data:{"userId":userId,"bookName":bookName},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					var bookId = result.data.cn_notebook_id;
					var bookName = result.data.cn_notebook_name;
					closeWindow();
					createBookLi(bookId,bookName);
				}else {
					alert("创建笔记本失败");
				}
			},
			error:function(){
				alert("创建笔记本失败");
			}
			
		});
	}
	
}

function renameBook(){
	var userId=getCookie("userId");
	if(userId==""){
		window.location.href="log_in.html";
	}else{
		var $li = $("#book_ul a.checked").parent();
		var bookId = $li.data("bookId");
		var bookName = $("#input_notebook_rename").val().trim();
		$.ajax({
			url:path+"/book/rename.do",
			type:"post",
			data:{"bookName":bookName,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					var bookName = result.data.cn_notebook_name;
					var bookId = result.data.cn_notebook_id;
					$("#book_ul a.checked").val(bookName);
					alert("修改成功");
					$("#book_ul a.checked").parent().remove();
					createBookLi(bookId,bookName);
					alert("修改成功")
					//window.location.reload();
				}else if(result.state==2){
					alert(result.message);
				}
			},
			error:function(){
				alert("修改笔记本失败")
			}
		});
	}
}

function deleteBook(){
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	var userId=getCookie("userId");
	if(userId==""){
		window.location.href="log_in.html";
	}else{
		$.ajax({
			url:path+"/book/deletebook.do",
			type:"post",
			data:{"userId":userId,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.state==0&&result.data==true){
					$("#book_ul a.checked").parent().remove();
					alert("删除成功");
				}else{
					alert("服务器异常，删除失败");
				}
			},
			error:function(){
				alert("删除失败");
			}
		});
	}
}