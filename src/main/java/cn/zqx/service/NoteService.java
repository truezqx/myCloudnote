package cn.zqx.service;

import java.util.List;
import java.util.Map;

import cn.zqx.entity.Note;

public interface NoteService {
	List<Map> loadNotes(String bookId) throws NotebookNotFoundException;
	Note loadNote(String noteId) throws NoteNotFoundException;
	boolean updateNote(String noteId,String title,String body);
	Note addNote(String userId,String bookId,String noteTitle);
	Note removeNote(String userId,String noteId);
	Note moveNote(String userId,String noteId,String bookId);
	
}
