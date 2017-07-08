package cn.zqx.service;

import java.util.List;

import cn.zqx.entity.Share;

public interface ShareService {
	
	Share addShareNote(String userId,String noteId);
	List<Share> findLikeTitle(String keyword,int page);
	Share findById(String shareId);
}
