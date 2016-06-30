package cn.qdu.qq.bis;

import java.awt.peer.DialogPeer;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import cn.qdu.qq.util.DialogUtil;
import cn.qdu.qq.util.ObjectUtil;
import cn.qdu.qq.vo.AddFriendsMsg;
import cn.qdu.qq.vo.AddRSMsg;
import cn.qdu.qq.vo.Find;
import cn.qdu.qq.vo.RegeditRs;
import cn.qdu.qq.vo.User;
import cn.qdu.ui.FindRSJFrame;
import cn.qdu.ui.MainJFrame;

public class SysBis {
	/**
	 * 验证用户名密码是否正确
	 * @param u
	 * @return返回null代表错误，如果返回Users（所有信息）
	 */
	private Socket s;
	public SysBis(Socket s){
		this.s=s;
	}
public User login(User u) throws IOException, ClassNotFoundException{
	//1.把Users对象写入服务端
	ObjectUtil.writerObject(s, u);
	//2.读取服务端的验证界面
	return (User) ObjectUtil.readObject(s);
}
/**
 * 注册方法
 * @param u
 * @throws IOException 
 * @throws ClassNotFoundException 
 */
public RegeditRs regedit(User u) throws IOException, ClassNotFoundException{
	//1.把Users对象写入服务端
		ObjectUtil.writerObject(s, u);
		//2.读取服务端的验证界面
		return (RegeditRs) ObjectUtil.readObject(s);
	
}

@SuppressWarnings("unchecked")
public void find(Find find) throws ClassNotFoundException, IOException{
	ObjectUtil.writerObject(s, find);
//	return (List<User>) ObjectUtil.readObject(s);
	
}
public  void showFindRS(List<User> ulist,User u){
	if (ulist.size()==0) {
		//没有查询出来结果
		DialogUtil.showAlarm("没有查询到结果！");
	}else {
		FindRSJFrame rs=new FindRSJFrame(ulist,u,s);
		rs.setLocationRelativeTo(null);
		rs.setVisible(true);
	}
}
public void showAddMsg(AddFriendsMsg msg,MainJFrame mj) throws IOException{
	//弹出确认对话框
	boolean b=DialogUtil.showconfirm(msg.getFrom().getNickname()+"请求加你为好友，是否同意？");
	if (b) {
		//同意-->添加头像到主窗口
		System.out.println("同意！");
		mj.addFriend(msg.getFrom());
	}else {
		//不同意
		System.out.println("不同意！");
		
	}
	AddRSMsg rs=new AddRSMsg();
	rs.setFrom(msg.getTo());
	rs.setTo(msg.getFrom());
	rs.setAgree(b);
	ObjectUtil.writerObject(s, rs);//把消息发送给服务器
}

public void showAddRs(AddRSMsg msg,MainJFrame mj){
	if (msg.isAgree()) {
		//同意
		DialogUtil.showInfo(msg.getFrom().getNickname()+"同意了你的好友请求！");
		mj.addFriend(msg.getFrom());
	}else {
		//不同意
		DialogUtil.showAlarm(msg.getFrom().getNickname()+"拒绝了你的好友请求！");
	}
}
}
