package cn.zqx.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zqx.dao.NoteDao;
import cn.zqx.dao.NotebookDao;
import cn.zqx.entity.Note;
import cn.zqx.entity.Notebook;
import cn.zqx.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteDao noteDao;
	@Resource
	private NotebookDao notebookDao;
	public List<Map> loadNotes(String bookId)  throws NotebookNotFoundException{
		if(bookId==null||bookId.trim().isEmpty()){
			throw new NotebookNotFoundException("ID为空");
		}
		Notebook notebook = notebookDao.findNotebookById(bookId);
		if(notebook==null){
			throw new NotebookNotFoundException("笔记本不存在");
		}
		List<Map> notes = noteDao.findNotesById(bookId);
		return notes;
	}

	public Note loadNote(String noteId) throws NoteNotFoundException{
		if(noteId==null||noteId.trim().isEmpty()){
			throw new NoteNotFoundException("笔记不存在");
		}
		Note note = noteDao.findNoteById(noteId);
		return note;
	}


	public boolean updateNote(String noteId, String title, String body) {
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		System.out.println(note);
		int row= noteDao.updateNoteById(note);
		return row==1;
	}

	public Note addNote(String userId, String bookId, String noteTitle) {
		Note note = new Note();
		note.setCn_note_id(NoteUtil.createId());
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(noteTitle);
		Long time = System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_last_modify_time(null);
		note.setCn_note_type_id("1");
		note.setCn_note_body("");
		note.setCn_note_status_id("1");
		int row = noteDao.addNote(note);
		if(row==1){
			return note;
		}
		return null;
	}

	public Note removeNote(String userId, String noteId) {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("用户ID为空");
		}
		Note note = noteDao.findNoteById(noteId);
		System.out.println(userId+"   "+note.getCn_user_id());
		if(note.getCn_user_id().equals(userId)){
			note = new Note();
			note.setCn_note_id(noteId);
			note.setCn_note_status_id("2");
			Long time = System.currentTimeMillis();
			note.setCn_note_last_modify_time(time);
			int row = noteDao.removeNote(note);
			if(row==1){
				return note;
			}
			return null;
		}else{
			throw new UserNotFoundException("没有权限");
		}
		
	}

	public Note moveNote(String userId, String noteId, String bookId) {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("用户ID为空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note.getCn_user_id().equals(userId)){
			note = new Note();
			note.setCn_note_id(noteId);
			note.setCn_notebook_id(bookId);
			Long time = System.currentTimeMillis();
			note.setCn_note_last_modify_time(time);
			int row = noteDao.moveNote(note);
			if(row==1){
				return note;
			}
			return null;
		}else{
			throw new UserNotFoundException("没有权限");
		}
	}

	

}
