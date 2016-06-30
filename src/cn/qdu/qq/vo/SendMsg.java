package cn.qdu.qq.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMsg implements Serializable {

	private User from;
	private User to;
	private String msg;
	private Date time;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return from.getNickname()+": "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time)+"\n"+msg+"\n\n";
	}
}
