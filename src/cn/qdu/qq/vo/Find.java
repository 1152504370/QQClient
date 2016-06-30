package cn.qdu.qq.vo;

import java.io.Serializable;

public class Find implements Serializable {
private int type;//查找类型（1--精确2--所有）
private String faccount;//查找帐号
private String from;//当前用户账号
private String nickname;//用于昵称模糊搜索
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
