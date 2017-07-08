
				//加载笔记列表,给笔记本li绑定单击事件
				function loadBookNotes(){
					$("#pc_part_4").hide();
					$("#pc_part_6").hide();
					$("#pc_part_7").hide();
					$("#pc_part_8").hide();
					$("#pc_part_2").show();
					//设置笔记本li选中效果
					$("#book_ul a").removeClass("checked");
					$(this).find("a").addClass("checked");
					//获取请求参数
					var bookId = $(this).data("bookId");
					$.ajax({
						"url":path+"/note/loadNotes.do",
						"type":"post",
						"data":{"bookId":bookId},
						"dataType":"json",
						"success":function(result){
							if(result.state==0){
								//清空原有的笔记列表
								$("#note_ul li").remove();
								var notes = result.data;
								for(var i=0;i<notes.length;i++){
									var noteId = notes[i].cn_note_id;
									var noteTitle = notes[i].cn_note_title;
									var noteBody = notes[i].cn_note_body;
									createNoteLi(noteId,noteTitle);
									var typeId=notes[i].cn_note_type_id;
									if(typeId=="2"){
										var img = '<i class="fa fa-star"></i>';
										var $li=$("#note_ul li:last");
										$li.find(".fa-file-text-o").after(img);
									}
								}
								
							}
						},
						"error":function(){
							alert("获取笔记失败！")
						}
					});
				}
				

				//获取笔记信息
				function getNote(){
					$("#note_ul a").removeClass("checked");
					$(this).find("a").addClass("checked");
					$("#pc_part_3").show();
					$("#pc_part_5").hide();
					var noteId=$(this).data("noteId");
					$.ajax({
						"url":path+"/note/getNote.do",
						"type":"post",
						"data":{"noteId":noteId},
						"dataType":"json",
						"success":function(result){
							if(result.state==0){
								//获取笔记信息
								var note = result.data;
								var noteTitle = note.cn_note_title;
								var noteBody = note.cn_note_body;
								//设置到编辑区域
								$("#input_note_title").val(noteTitle);
								um.setContent(noteBody);
							}
						},
						"error":function(){
							alert("获取笔记失败");
						}
					});
				}
				
				
				function updateNote(){
					var title = $("#input_note_title").val().trim();
					var body = um.getContent();
					var $li = $("#note_ul a.checked").parent();
					var noteId = $li.data("noteId");
					if($li.length==0){
						alert("请先选择要保存的笔记！");
					}else if(title==""){
							alert("标题不能为空！");
					}else{
						$.ajax({
							url:path+"/note/updateNote.do",
							type:"post",
							data:{"noteId":noteId,"title":title,"body":body},
							dataType:"json",
							success:function(result){
								if(result.state==0){
									if(result.data==true){
										
										//更新列表li中
										var sli="";
										sli+='	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' +title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
										//将选中的li中的a内容替换掉
										$li.find("a").html(sli);
										alert("保存笔记成功");
									}else{
										alert("保存失败，稍后重试")
									}
								}
							},
							error:function(){
								alert("保存笔记异常");
							}
						});
					}
						
				}
				
				function createNote(){
					var $li = $("#book_ul a.checked").parent();
					var bookId = $li.data("bookId");
					var userId=getCookie("userId");
					var noteTitle = $("#input_note").val().trim();
					var ok=true;
					if(userId==""){
						ok=false;
						Window.location.href("log_in.html");
					}
					if(noteTitle==""){
						ok=false;
						$("#note_span").html("笔记名称不能为空！");
					}
					if(ok){
						$.ajax({
							url:path+"/note/addnote.do",
							type:"post",
							data:{"userId":userId,"bookId":bookId,"noteTitle":noteTitle},
							dataType:"json",
							success:function(result){
								if(result.state==0){
									var noteId=result.data.cn_note_id;
									var noteTitle=result.data.cn_note_title;
									createNoteLi(noteId,noteTitle);
									closeWindow();
								}
							},
							error:function(){
								alert("创建笔记失败！")
							}
						});
					}
				}
				
				function createNoteLi(noteId,noteTitle){
					var sli="";
					sli+='<li class="online">';
					sli+='<a  >';
					sli+='	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' +noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					sli+='</a>';
					sli+='<div class="note_menu" tabindex="-1">';
					sli+='	<dl>';
					sli+='		<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
					sli+='		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
					sli+='		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
					sli+='	</dl>';
					sli+='</div>';
				sli+='</li>';
				//将noteId绑定到li元素上
				var $li = $(sli);
				$li.data("noteId",noteId);
				$("#note_ul").append($li);
				}
				

				function popNoteMenu(){
					
					//显示菜单
					var $menu = $(this).parent().next();
					$menu.slideDown(500);
					//设置点击笔记选中效果
					$("#note_ul a").removeClass("checked");
					$(this).parent().addClass("checked");
					//阻止事件冒泡
					return false;
				}	
				
				function hideNoteMenu(){
					$("#note_ul div").hide();
				}
				//删除笔记
				function removeNote(){
					var $li = $("#note_ul a.checked").parent();
					var noteId=$li.data("noteId");
					var userId=getCookie("userId");
					if(userId==null){
						Window.location.href("log_in.html");
					}
					$.ajax({
						url:path+"/note/removeNote.do",
						type:"post",
						data:{"userId":userId,"noteId":noteId},
						dataType:"json",
						success:function(result){
							if(result.state==0){
								var noteId=result.data.cn_note_id;
								$li.remove();
								alert("移动至回收站");
							}else{
								alert(result.message);
							}
						},
						error:function(){
							alert("删除笔记失败！")
						}
					});
				}
				//移动笔记
				function moveNote(){
					$("#moveSelect_span").html("");
					var $li = $("#note_ul a.checked").parent();
					var noteId = $li.data("noteId");
					var bookId = $("#moveSelect").val().trim();
					var userId = getCookie("userId");
					if(userId==""){
						Window.location.href("log_in.html");
					}
					if(bookId=="none"){
						$("#moveSelect_span").html("请选择一个笔记本!");
					}else{
						$.ajax({
							"url":path+"/note/moveNote.do",
							"type":"post",
							"data":{"userId":userId,"noteId":noteId,"bookId":bookId},
							"dataType":"json",
							"success":function(result){
								if(result.state==0){
									//移除对应Li元素
									$li.remove();
									alert("笔记移动成功");
								}else{
									alert(result.message);
								}
							},
							"error":function(){
								alert("移动笔记失败！")
							}
						});
					}
				}
				
				function shareNote(){
					var $li = $("#note_ul a.checked").parent();
					//var $li = $(this).parents("li");
					var noteId = $li.data("noteId");
					var userId = getCookie("userId");
					if(userId==""){
						Window.location.href("log_in.html");
					}else{
						$.ajax({
							url:path+"/share/shareNote.do",
							type:"post",
							data:{"userId":userId,"noteId":noteId},
							dataType:"json",
							success:function(result){
								if(result.state==0){
									var img='<i class="fa fa-star"></i>';
									$li.find(".fa-file-text-o").after(img);
									alert("分享成功");
								}else{
									alert(result.message);
								}
							},
							error:function(){
								alert("服务器异常，分享失败");
							}
						});
					}
				}
				
				//分页加载
				function searchSharePage(keyword,page){
					$.ajax({
						url:path+"/share/searchShare.do",
						type:"post",
						data:{"keyword":keyword,"page":page},
						dataType:"json",
						success:function(result){
							if(result.state==0){
								var shareNotes = result.data;
								for(var i=0;i<shareNotes.length;i++){
									var shareId=shareNotes[i].cn_share_id;
									var shareTitle=shareNotes[i].cn_share_title;
									//生成li
									var sli="";
									sli+='<li class="online">';
									sli+='<a  >';
									sli+='	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' +shareTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-star"></i></button>';
									sli+='</a>';
									sli+='</li>';
									var $li = $(sli);
									$li.data("shareId",shareId);
									$("#pc_part_6 ul").append($li);
									
								}
							}
						},
						error:function(){
							alert("服务器异常，搜索失败");
						}
					});
				}
				
				function loadShareNote(){
					$("#share_ul a").removeClass("checked");
					$(this).find("a").addClass("checked");
					$("#pc_part_3").hide();
					$("#pc_part_5").show();
					var $li =$("#share_ul a.checked").parent();
					var shareId = $li.data("shareId");
					var userId = getCookie("userId");
					if(userId==""){
						Window.location.href("log_in.html");
					}else{
						$.ajax({
							url:path+"/share/loadShareNote.do",
							type:"post",
							data:{"shareId":shareId},
							dataType:"json",
							success:function(result){
								if(result.state==0){
									var shareTitle = result.data.cn_share_title;
									var shareBody = result.data.cn_share_body;
									//显示标题
									$("#noput_note_title").html(shareTitle);
									//显示body
									$("#noput_note_title").next().html(shareBody);
								}else{
									alert(result.message);
								}
							},
							error:function(){
								alert("加载笔记失败");
							}
						});
					}
					
				}