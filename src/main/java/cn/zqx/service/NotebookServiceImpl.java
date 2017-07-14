package cn.zqx.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zqx.dao.NotebookDao;
import cn.zqx.dao.UserDao;
import cn.zqx.entity.Notebook;
import cn.zqx.entity.User;
import cn.zqx.util.NoteUtil;

@Service("notebookService")
public class NotebookServiceImpl implements NotebookService{
	@Resource(name="notebookDao")
	private NotebookDao notebookDao;
	@Resource(name="userDao")
	private UserDao userDao;

	public List<Notebook> listNotebooks(String userId) throws UserNotFoundException{
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("ID为空");
		}
		User user = userDao.findById(userId);
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}
		return notebookDao.findNotebookByUserId(userId);
	}

	public Notebook addNotebook(String userId,String bookName)throws UserNotFoundException,NotebookNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("用户ID为空");
		} else if(bookName==null||bookName.trim().isEmpty()){
			throw new NotebookNotFoundException("笔记本名不能为空");
		}
		
		User user = userDao.findById(userId);
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}
		Notebook notebook = new Notebook();
		notebook.setCn_notebook_id(NoteUtil.createId());
		notebook.setCn_notebook_name(bookName);
		notebook.setCn_notebook_type_id("5");
		notebook.setCn_user_id(userId);
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		notebook.setCn_notebook_createtime(sdf.format(now));
		int row = notebookDao.addNotebook(notebook);
		if(row==1){
			return notebook;
		}else{
			return null;
		}
		
		
	}

	public Notebook renameBook(String bookName, String bookId) {
		Notebook notebook = notebookDao.findNotebookById(bookId);
		if(notebook==null){
			throw new NotebookNotFoundException("笔记本不存在");
		}
		notebook.setCn_notebook_name(bookName);	
		int rows = notebookDao.renameBook(notebook);
		if(rows>=1){
			return notebook;
		}
		return null;
	}

	public boolean deleteBook(String bookId) {
		int rows = notebookDao.deleteBook(bookId);
		return rows>=1;
	}

}
