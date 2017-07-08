package cn.zqx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zqx.dao.NoteDao;
import cn.zqx.dao.ShareDao;
import cn.zqx.entity.Note;
import cn.zqx.entity.Share;
import cn.zqx.util.NoteUtil;

@Service("shareService")
public class ShareServiceimpl implements ShareService{
	@Resource
	private ShareDao shareDao;
	@Resource
	private NoteDao noteDao;
	
	@Transactional
	public Share addShareNote(String userId,String noteId) {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("用户ID为空");
		}
		Share share = shareDao.findByNoteId(noteId);
		if(share!=null){
			throw new ShareException("笔记已被分享");
		}
		Note note = new Note();
		note.setCn_note_type_id("2");
		note.setCn_note_id(noteId);
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		noteDao.shareNote(note);
		
		note = noteDao.findNoteById(noteId);
		String cn_share_id = NoteUtil.createId();
		String cn_share_title=note.getCn_note_title();
		String cn_share_body=note.getCn_note_body();
		share = new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_body(cn_share_body);
		share.setCn_share_id(cn_share_id);
		share.setCn_share_title(cn_share_title);
		int row = shareDao.addShareNote(share);
		if(row>=1){
			return share;
		}
		return null;
	}

	public List<Share> findLikeTitle(String keyword,int page) {
		String title = "%";
		if(keyword!=null&&!"".equals(keyword)){
			title = "%"+keyword+"%";
		}
		if(page<1){
			page=1;
		}
		Integer begin = (page-1)*5;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("keyword",title);
		params.put("begin", begin);
		List<Share> shareNotes = shareDao.findLikeTitle(params);
		return shareNotes;
	}

	public Share findById(String shareId) {
		Share share = shareDao.findById(shareId);
		return share;
	}


}
