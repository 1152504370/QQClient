package cn.qdu.qq.vo;

import java.io.Serializable;

public class RegeditRs implements Serializable {
private boolean rs;//注册成功或失败
private String msg;//注册结果
public boolean isRs() {
	return rs;
}
public void setRs(boolean rs) {
	this.rs = rs;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}

}
