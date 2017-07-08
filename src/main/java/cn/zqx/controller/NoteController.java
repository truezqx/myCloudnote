package cn.zqx.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zqx.entity.Note;
import cn.zqx.service.NoteService;
import cn.zqx.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController extends BaseController{
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/loadNotes.do")
	@ResponseBody
	public JsonResult loadNotes(String bookId){
		List<Map> notes = noteService.loadNotes(bookId);
		return new JsonResult(notes);
	}
	@RequestMapping("/getNote.do")
	@ResponseBody
	public JsonResult getNote(String noteId){
		Note note = noteService.loadNote(noteId);	
		return new JsonResult(note);
	}
	@RequestMapping("/updateNote.do")
	@ResponseBody
	public JsonResult updateNote(String noteId,String title,String body){
		//System.out.println(noteId+"------- "+title+"------- "+body);
		boolean success = noteService.updateNote(noteId, title, body);
		return new JsonResult(success);
	}
	
	@RequestMapping("/addnote.do")
	@ResponseBody
	public JsonResult addnote(String userId,String bookId,String noteTitle){
		Note note = noteService.addNote(userId, bookId, noteTitle);
		return new JsonResult(note);
	}
	
	@RequestMapping("/removeNote.do")
	@ResponseBody
	public JsonResult removeNote(String userId,String noteId){
		Note note = noteService.removeNote(userId, noteId);
		return new JsonResult(note);
	}
	
	@RequestMapping("/moveNote.do")
	@ResponseBody
	public JsonResult movaNote(String userId,String noteId,String bookId){
		Note note = noteService.moveNote(userId, noteId, bookId);
		return new JsonResult(note);
	}


}
