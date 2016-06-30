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
	 * ��֤�û��������Ƿ���ȷ
	 * @param u
	 * @return����null��������������Users��������Ϣ��
	 */
	private Socket s;
	public SysBis(Socket s){
		this.s=s;
	}
public User login(User u) throws IOException, ClassNotFoundException{
	//1.��Users����д������
	ObjectUtil.writerObject(s, u);
	//2.��ȡ����˵���֤����
	return (User) ObjectUtil.readObject(s);
}
/**
 * ע�᷽��
 * @param u
 * @throws IOException 
 * @throws ClassNotFoundException 
 */
public RegeditRs regedit(User u) throws IOException, ClassNotFoundException{
	//1.��Users����д������
		ObjectUtil.writerObject(s, u);
		//2.��ȡ����˵���֤����
		return (RegeditRs) ObjectUtil.readObject(s);
	
}

@SuppressWarnings("unchecked")
public void find(Find find) throws ClassNotFoundException, IOException{
	ObjectUtil.writerObject(s, find);
//	return (List<User>) ObjectUtil.readObject(s);
	
}
public  void showFindRS(List<User> ulist,User u){
	if (ulist.size()==0) {
		//û�в�ѯ�������
		DialogUtil.showAlarm("û�в�ѯ�������");
	}else {
		FindRSJFrame rs=new FindRSJFrame(ulist,u,s);
		rs.setLocationRelativeTo(null);
		rs.setVisible(true);
	}
}
public void showAddMsg(AddFriendsMsg msg,MainJFrame mj) throws IOException{
	//����ȷ�϶Ի���
	boolean b=DialogUtil.showconfirm(msg.getFrom().getNickname()+"�������Ϊ���ѣ��Ƿ�ͬ�⣿");
	if (b) {
		//ͬ��-->���ͷ��������
		System.out.println("ͬ�⣡");
		mj.addFriend(msg.getFrom());
	}else {
		//��ͬ��
		System.out.println("��ͬ�⣡");
		
	}
	AddRSMsg rs=new AddRSMsg();
	rs.setFrom(msg.getTo());
	rs.setTo(msg.getFrom());
	rs.setAgree(b);
	ObjectUtil.writerObject(s, rs);//����Ϣ���͸�������
}

public void showAddRs(AddRSMsg msg,MainJFrame mj){
	if (msg.isAgree()) {
		//ͬ��
		DialogUtil.showInfo(msg.getFrom().getNickname()+"ͬ������ĺ�������");
		mj.addFriend(msg.getFrom());
	}else {
		//��ͬ��
		DialogUtil.showAlarm(msg.getFrom().getNickname()+"�ܾ�����ĺ�������");
	}
}
}
