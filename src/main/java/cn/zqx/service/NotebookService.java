package cn.zqx.service;

import java.util.List;

import cn.zqx.entity.Notebook;

public interface NotebookService {
	List<Notebook> listNotebooks(String userId) throws UserNotFoundException;
	Notebook addNotebook(String userId,String bookName)throws UserNotFoundException;
	Notebook removeBook(String userId,String bookId)throws UserNotFoundException;
}
