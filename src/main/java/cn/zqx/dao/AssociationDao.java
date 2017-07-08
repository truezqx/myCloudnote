package cn.zqx.dao;

import java.awt.print.Book;
import java.util.List;

import cn.zqx.entity.Notebook;

public interface AssociationDao {
	public Notebook findById(String bookId);
	public List<Notebook> findAllBook();

}
