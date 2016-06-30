package cn.qdu.qq.bis;

import java.io.IOException;
import java.net.Socket;

import cn.qdu.qq.util.ObjectUtil;
import cn.qdu.qq.vo.AddFriendsMsg;
import cn.qdu.qq.vo.SendMsg;
import cn.qdu.ui.MainJFrame;

/**
 * 和用户相关的方法
 * @author wang
 *
 */
public class UserBis {
private Socket s;//连接到服务器的Socket

public UserBis(Socket s){
	this.s=s;
}
public void addFriends(AddFriendsMsg msg) throws IOException{
	ObjectUtil.writerObject(s, msg);//发送添加好友请求给服务器
}
/**
 * 发送消息给好友
 * @param msg
 * @throws IOException
 */
public void sendMsg(SendMsg msg) throws IOException{
	ObjectUtil.writerObject(s, msg);
}
/**
 * 显示好友发送的信息
 * @param msg
 */
public void showMsg(SendMsg msg,MainJFrame mj){
	mj.tip(msg);//显示消息
}
}
