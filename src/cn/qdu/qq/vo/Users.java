package cn.qdu.qq.vo;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable{
private String account;
private String password;
private String nickname;
private int age;
private String email;
private String img;
//===========================
private List<Users> friends;//此用户所有的好友
public Users(String password,
String nickname,
int age,
String email,
String img){
	this.password=password;
	this.nickname=nickname;
	this.age=age;
	this.email=email;
	this.img=img;
}
public Users(){
	
}
public Users(String account,String password){
	this.account=account;
	this.password=password;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public List<Users> getFriends() {
	return friends;
}
public void setFriends(List<Users> friends) {
	this.friends = friends;
}

}
