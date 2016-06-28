package cn.qdu.qq.bis;

import java.io.IOException;
import java.net.Socket;

import cn.qdu.qq.util.ObjectUtil;
import cn.qdu.qq.vo.Users;

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
public Users login(Users u) throws IOException, ClassNotFoundException{
	//1.把Users对象写入服务端
	ObjectUtil.writerObject(s, u);
	//2.读取服务端的验证界面
	return (Users) ObjectUtil.readObject(s);
}
}
