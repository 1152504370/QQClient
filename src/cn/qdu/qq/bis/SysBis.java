package cn.qdu.qq.bis;

import java.io.IOException;
import java.net.Socket;

import cn.qdu.qq.util.ObjectUtil;
import cn.qdu.qq.vo.Users;

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
public Users login(Users u) throws IOException, ClassNotFoundException{
	//1.��Users����д������
	ObjectUtil.writerObject(s, u);
	//2.��ȡ����˵���֤����
	return (Users) ObjectUtil.readObject(s);
}
}
