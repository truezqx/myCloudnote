/** 封装弹出对话框处理js**/

function alertAddBookWindow(){
	$("#can").load("alert/alert_notebook.html");
	$(".opacity_bg").show();
}

function alertAddNoteWindow(){
	var $a = $("#book_ul a.checked");
	if($a.length==0){
		alert("请先选择笔记本");
	}else{
		$("#can").load("alert/alert_note.html");
		$(".opacity_bg").show();
	}
	
}

function closeWindow(){
	$("#can").empty();
	$(".opacity_bg").hide();
}

function alertRenameWindow(){
	$("#can").load("alert/alert_rename.html");
	$(".opacity_bg").show();
}

function alertDeleteBook(){
	$("#can").load("alert/alert_delete_notebook.html");
	$(".opacity_bg").show();
}

function alertDeleteNote(){
	$("#can").load("alert/alert_delete_note.html");
	$(".opacity_bg").show();
}
function alertMoveNote(){
	$("#can").load("alert/alert_move.html",function(){
		//获取book_ul中所有的li
		var books = $("#book_ul li");
		//循环li获取所有笔记本ID和Nanme
		for(var i=0;i<books.length;i++){
			//获取li元素并转为jquery对象
			var $li=$(books[i]);
			var bookId=$li.data("bookId");
			//获取bookName
			var bookName = $li.text().trim();
			//生成option
			var sopt = "";
			sopt+="<option value="+"'"+bookId+"'"+">"+ bookName+"</option>";
			//将option添加到对话框的select中
			$("#moveSelect").append(sopt);
		}
	});
	$(".opacity_bg").show();
	
}

