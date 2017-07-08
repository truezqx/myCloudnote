package cn.zqx.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zqx.entity.Share;
import cn.zqx.service.NoteService;
import cn.zqx.service.ShareService;
import cn.zqx.util.JsonResult;

@Controller
@RequestMapping("/share")
public class ShareController extends BaseController{
	@Resource
	private ShareService shareService;
	@Resource
	private NoteService noteService;
	
	
	@RequestMapping("/shareNote.do")
	@ResponseBody
	public JsonResult addShareNote(String userId,String noteId){
		
		Share share = shareService.addShareNote(userId,noteId);

		return new JsonResult(share);
		
		
	}
	
	@RequestMapping("/searchShare.do")
	@ResponseBody
	public JsonResult searchShare(String keyword,int page){
		List<Share> shareNotes = shareService.findLikeTitle(keyword, page);
		return new JsonResult(shareNotes);
	}
	
	@RequestMapping("/loadShareNote.do")
	@ResponseBody
	public JsonResult loadShareNote(String shareId){
		Share share = shareService.findById(shareId);
		return new JsonResult(share);
	}

}
