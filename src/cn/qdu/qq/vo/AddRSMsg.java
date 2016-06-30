package cn.qdu.qq.vo;

import java.io.Serializable;

public class AddRSMsg implements Serializable {
	private User from;
	private User to;
	private boolean agree;
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
	public boolean isAgree() {
		return agree;
	}
	public void setAgree(boolean agree) {
		this.agree = agree;
	}

}
