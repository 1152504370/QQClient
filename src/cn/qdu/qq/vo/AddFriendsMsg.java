package cn.qdu.qq.vo;

import java.io.Serializable;

public class AddFriendsMsg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User from;
	private User to;
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}

}
