package cn.zqx.dao;

import java.util.List;

import cn.zqx.entity.Notebook;

public interface NotebookDao {
	public List<Notebook> findNotebookByUserId(String userId);
	public Notebook findNotebookById(String notebookId);
	public int addNotebook(Notebook notebook);
	public int removeBook(Notebook notebook);
}
