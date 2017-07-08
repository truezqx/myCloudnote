package cn.zqx.entity;

import java.io.Serializable;

public class User implements Serializable{


	private static final long serialVersionUID = 4628398318742289497L;
	private String cn_user_id;
	private String cn_user_name;
	private String cn_user_password;
	private String cn_user_token;
	private String cn_user_nick;
	

	public User(){};

	public User(String cn_user_id, String cn_user_name, String cn_user_password, String cn_user_token,
			String cn_user_nick) {
		super();
		this.cn_user_id = cn_user_id;
		this.cn_user_name = cn_user_name;
		this.cn_user_password = cn_user_password;
		this.cn_user_token = cn_user_token;
		this.cn_user_nick = cn_user_nick;
	}


	public String getCn_user_id() {
		return cn_user_id;
	}


	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}


	public String getCn_user_name() {
		return cn_user_name;
	}


	public void setCn_user_name(String cn_user_name) {
		this.cn_user_name = cn_user_name;
	}


	public String getCn_user_password() {
		return cn_user_password;
	}


	public void setCn_user_password(String cn_user_password) {
		this.cn_user_password = cn_user_password;
	}


	public String getCn_user_token() {
		return cn_user_token;
	}


	public void setCn_user_token(String cn_user_token) {
		this.cn_user_token = cn_user_token;
	}


	public String getCn_user_nick() {
		return cn_user_nick;
	}


	public void setCn_user_nick(String cn_user_nick) {
		this.cn_user_nick = cn_user_nick;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "User [cn_user_id=" + cn_user_id + ", cn_user_name=" + cn_user_name + ", cn_user_password="
				+ cn_user_password + ", cn_user_token=" + cn_user_token + ", cn_user_nick=" + cn_user_nick + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cn_user_id == null) ? 0 : cn_user_id.hashCode());
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
		User other = (User) obj;
		if (cn_user_id == null) {
			if (other.cn_user_id != null)
				return false;
		} else if (!cn_user_id.equals(other.cn_user_id))
			return false;
		return true;
	};
	
	
	
	

}
