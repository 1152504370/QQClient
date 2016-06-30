package cn.qdu.qq.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import cn.qdu.qq.bis.SysBis;
import cn.qdu.qq.bis.UserBis;
import cn.qdu.qq.util.ObjectUtil;
import cn.qdu.qq.vo.AddFriendsMsg;
import cn.qdu.qq.vo.AddRSMsg;
import cn.qdu.qq.vo.SendMsg;
import cn.qdu.qq.vo.User;
import cn.qdu.ui.MainJFrame;

public class ServerThread extends Thread{
private Socket s;//连接到服务器的Socket
private User u;
private SysBis sBis;
private MainJFrame mj;
private UserBis uBis;

public ServerThread(Socket s,User u,MainJFrame mj){
	this.s=s;
	this.u=u;
	this.mj=mj;
	sBis=new SysBis(s);
	uBis=new UserBis(s);
}
public void run(){
	while (true) {
		try {
			Object o=ObjectUtil.readObject(s);
			System.out.println("服务器发送的消息:"+o);
			if (o instanceof List) {
				//查询结果-->List<User> 代码重构！！
				List<User> ulist=(List<User>) o;
				System.out.println("查询结果长度： "+ulist.size());
			    sBis.showFindRS(ulist, u);
			}else if (o instanceof AddFriendsMsg) {
				AddFriendsMsg msg=(AddFriendsMsg) o;
				sBis.showAddMsg(msg,mj);
			}else if (o instanceof AddRSMsg) {
				AddRSMsg rs=(AddRSMsg)o;
				sBis.showAddRs(rs, mj);
			}else if (o instanceof SendMsg) {
				SendMsg msg=(SendMsg) o;
				uBis.showMsg(msg, mj);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
}
