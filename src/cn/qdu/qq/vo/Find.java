package cn.qdu.qq.vo;

import java.io.Serializable;

public class Find implements Serializable {
private int type;//�������ͣ�1--��ȷ2--���У�
private String faccount;//�����ʺ�
private String from;//��ǰ�û��˺�
private String nickname;//�����ǳ�ģ������
public static final int ONE=1;
public static final int ALL=2;
public static final int NAME=3;


public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public String getFaccount() {
	return faccount;
}
public void setFaccount(String faccount) {
	this.faccount = faccount;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
}
