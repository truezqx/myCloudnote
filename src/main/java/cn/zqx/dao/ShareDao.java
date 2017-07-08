package cn.zqx.dao;

import java.util.List;
import java.util.Map;

import cn.zqx.entity.Share;

public interface ShareDao {
	public int addShareNote(Share share);
	public Share findByNoteId(String noteId);
	public List<Share> findLikeTitle(Map<String,Object> params);
	public Share findById(String shareId);
}
