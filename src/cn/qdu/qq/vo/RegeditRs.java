package cn.qdu.qq.vo;

import java.io.Serializable;

public class RegeditRs implements Serializable {
private boolean rs;//ע��ɹ���ʧ��
private String msg;//ע����
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
