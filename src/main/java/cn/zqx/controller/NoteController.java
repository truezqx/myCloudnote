package cn.zqx.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zqx.entity.Note;
import cn.zqx.service.NoteService;
import cn.zqx.service.UserNotFoundException;
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
	
	@RequestMapping("/loadRollbackNote.do")
	@ResponseBody
	public JsonResult loadRollbackNote(String userId){
		List<Note> notes = noteService.loadRollbackNote(userId);
		return new JsonResult(notes);
	}
	
	@RequestMapping("/replay.do")
	@ResponseBody
	public JsonResult replay(String noteId){
		Note note = noteService.replayNote(noteId);
		return new JsonResult(note);
	}
	
	@RequestMapping("/deleteNote.do")
	@ResponseBody
	public JsonResult deleteNote(String userId,String noteId){
		boolean success = noteService.deleteById(userId, noteId);
		return new JsonResult(success);
	}

	@RequestMapping("/likeNote.do")
	@ResponseBody
	public JsonResult likeNote(String userId,String shareId){
		try{
			Note note = noteService.likeNote(userId, shareId);
			return new JsonResult(note);
		}catch(UserNotFoundException e){
			e.printStackTrace();
			return new JsonResult(8,e);
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(e);
		}
		
	}
	@RequestMapping("/showLikeNote.do")
	@ResponseBody
	public JsonResult showLikeNote(String userId){
		List<Note> notes = noteService.loadLikeNote(userId);
		return new JsonResult(notes);
	}
	
}
