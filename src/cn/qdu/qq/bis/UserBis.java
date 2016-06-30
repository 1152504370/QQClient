package cn.qdu.qq.bis;

import java.io.IOException;
import java.net.Socket;

import cn.qdu.qq.util.ObjectUtil;
import cn.qdu.qq.vo.AddFriendsMsg;
import cn.qdu.qq.vo.SendMsg;
import cn.qdu.ui.MainJFrame;

/**
 * ���û���صķ���
 * @author wang
 *
 */
public class UserBis {
private Socket s;//���ӵ���������Socket

public UserBis(Socket s){
	this.s=s;
}
public void addFriends(AddFriendsMsg msg) throws IOException{
	ObjectUtil.writerObject(s, msg);//������Ӻ��������������
}
/**
 * ������Ϣ������
 * @param msg
 * @throws IOException
 */
public void sendMsg(SendMsg msg) throws IOException{
	ObjectUtil.writerObject(s, msg);
}
/**
 * ��ʾ���ѷ��͵���Ϣ
 * @param msg
 */
public void showMsg(SendMsg msg,MainJFrame mj){
	mj.tip(msg);//��ʾ��Ϣ
}
}
