package cn.qdu.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.qdu.qq.thread.ServerThread;
import cn.qdu.qq.vo.SendMsg;
import cn.qdu.qq.vo.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

public class MainJFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel ;
	
	//======================
	private User u;
	private Socket s;
	//String->account 窗口
	private Map<String,ChatJFrame>  chats=new HashMap<String,ChatJFrame>();
	/**
	 * 初始化好友头像
	 */
	private void initFriends(){
		List<User> ulist=u.getFriends();
		for (final User f : ulist) {
		
			panel.add(initImg(f)); 
		}
	}
/**
 * 添加新增好友头像
 * @param f
 */
public void  addFriend(final User f){

		panel.add(initImg(f)); 
		this.getContentPane().validate();//重新更新界面
}
/**
 * 新建头像图标
 * @param f
 * @return
 */
private JLabel initImg(final User f){
	JLabel fimg=new JLabel();
	fimg.setIcon(new ImageIcon(MainJFrame.class.getResource("/img/icon/"+f.getImg()+".PNG")));
	fimg.setToolTipText(f.getAccount());
	fimg.setText(f.getNickname());
	//加上点击事件
	fimg.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (e.getClickCount()==2) {
				//判断是否双击鼠标
				fimg.setIcon(new ImageIcon(MainJFrame.class.getResource("/img/icon/"+f.getImg()+".PNG")));
				ChatJFrame cj=chats.get(f.getAccount());
				if (cj==null) {
					//第一次显示
				cj=new ChatJFrame(f,u,s);
				cj.setLocationRelativeTo(null);
				cj.setVisible(true);
				//把新建立的窗口放置到map中
				chats.put(f.getAccount(), cj);
				}else {
					//已有窗口
					cj.setVisible(true);
				}
				
			}
		}
	});
	return fimg;
}
/**
 * 让好友头像抖动
 * @param msg
 */
public void tip(SendMsg msg){
	
	//显示到聊天窗口
	ChatJFrame cj=chats.get(msg.getFrom().getAccount());
	if (cj==null) {
		cj=new ChatJFrame(msg.getFrom(), u, s);
		cj.setLocationRelativeTo(null);
		chats.put(msg.getFrom().getAccount(), cj);
	}
	cj.appendMsg(msg);//添加聊天消息
	if (!cj.isVisible()) {
		//如果窗口不可见，则头像跳动
		Component[] img=panel.getComponents();
		System.out.println("数量："+img.length);
		for (Component c : img) {
			//找到对应的头像
			JLabel label=(JLabel) c;
			if (label.getToolTipText().equals(msg.getFrom().getAccount())) {
				label.setIcon(new ImageIcon(MainJFrame.class.getResource("/img/icon/"+msg.getFrom().getImg()+".gif")));
				break;
			}
		}
	}
}
	/**
	 * Create the frame.
	 */
	public MainJFrame(User u,Socket s) {
		this.u=u;
		this.s=s;
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					MainJFrame.this.s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 755);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainJFrame.class.getResource("/img/main/1.PNG")));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FindJFrame fj=new FindJFrame(s,u);
				fj.setLocationRelativeTo(null);
				fj.setVisible(true);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(MainJFrame.class.getResource("/img/main/2.PNG")));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane))
						.addComponent(lblNewLabel, Alignment.LEADING)
						.addComponent(lblNewLabel_1, Alignment.LEADING))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1))
		);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(50, 1, 5, 5));
		contentPane.setLayout(gl_contentPane);
		setTitle(u.getNickname());//设置标题为用户昵称
		initFriends();
		new ServerThread(s,u,MainJFrame.this).start();//启动线程监听服务器发送的消息
	}
}
