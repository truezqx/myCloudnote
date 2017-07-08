package cn.zqx.entity;

import java.io.Serializable;

public class Notebook implements Serializable{

	private static final long serialVersionUID = -8454252107927824472L;
	private String cn_notebook_id;
	private String cn_user_id;
	private String cn_notebook_type_id;
	private String cn_notebook_name;
	private String cn_notebook_desc;
	private String cn_notebook_createtime;
	//关联User
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Notebook(){};
	public Notebook(String cn_notebook_id, String cn_user_id, String cn_notebook_type_id, String cn_notebook_name,
			String cn_notebook_desc, String cn_notebook_createtime) {
		super();
		this.cn_notebook_id = cn_notebook_id;
		this.cn_user_id = cn_user_id;
		this.cn_notebook_type_id = cn_notebook_type_id;
		this.cn_notebook_name = cn_notebook_name;
		this.cn_notebook_desc = cn_notebook_desc;
		this.cn_notebook_createtime = cn_notebook_createtime;
	}
	public String getCn_notebook_id() {
		return cn_notebook_id;
	}
	public void setCn_notebook_id(String cn_notebook_id) {
		this.cn_notebook_id = cn_notebook_id;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_notebook_type_id() {
		return cn_notebook_type_id;
	}
	public void setCn_notebook_type_id(String cn_notebook_type_id) {
		this.cn_notebook_type_id = cn_notebook_type_id;
	}
	public String getCn_notebook_name() {
		return cn_notebook_name;
	}
	public void setCn_notebook_name(String cn_notebook_name) {
		this.cn_notebook_name = cn_notebook_name;
	}
	public String getCn_notebook_desc() {
		return cn_notebook_desc;
	}
	public void setCn_notebook_desc(String cn_notebook_desc) {
		this.cn_notebook_desc = cn_notebook_desc;
	}
	public String getCn_notebook_createtime() {
		return cn_notebook_createtime;
	}
	public void setCn_notebook_createtime(String cn_notebook_createtime) {
		this.cn_notebook_createtime = cn_notebook_createtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Notebook [cn_notebook_id=" + cn_notebook_id + ", cn_user_id=" + cn_user_id + ", cn_notebook_type_id="
				+ cn_notebook_type_id + ", cn_notebook_name=" + cn_notebook_name + ", cn_notebook_desc="
				+ cn_notebook_desc + ", cn_notebook_createtime=" + cn_notebook_createtime + ", user=" + user + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cn_notebook_id == null) ? 0 : cn_notebook_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notebook other = (Notebook) obj;
		if (cn_notebook_id == null) {
			if (other.cn_notebook_id != null)
				return false;
		} else if (!cn_notebook_id.equals(other.cn_notebook_id))
			return false;
		return true;
	}

}
