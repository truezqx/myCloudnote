package cn.zqx.dao;

import java.util.List;
import java.util.Map;

import cn.zqx.entity.Note;

public interface NoteDao {
	//public List<Note> findNotesByBookId(String bookId);
	public List<Map> findNotesById(String bookId);
	public Note findNoteById(String noteId);
	public int updateNoteById(Note note);
	public int addNote(Note note);
	public int removeNote(Note note);
	public int moveNote(Note note);
	public int shareNote(Note note);
}
