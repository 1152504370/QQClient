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
	//String->account ����
	private Map<String,ChatJFrame>  chats=new HashMap<String,ChatJFrame>();
	/**
	 * ��ʼ������ͷ��
	 */
	private void initFriends(){
		List<User> ulist=u.getFriends();
		for (final User f : ulist) {
		
			panel.add(initImg(f)); 
		}
	}
/**
 * �����������ͷ��
 * @param f
 */
public void  addFriend(final User f){

		panel.add(initImg(f)); 
		this.getContentPane().validate();//���¸��½���
}
/**
 * �½�ͷ��ͼ��
 * @param f
 * @return
 */
private JLabel initImg(final User f){
	JLabel fimg=new JLabel();
	fimg.setIcon(new ImageIcon(MainJFrame.class.getResource("/img/icon/"+f.getImg()+".PNG")));
	fimg.setToolTipText(f.getAccount());
	fimg.setText(f.getNickname());
	//���ϵ���¼�
	fimg.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (e.getClickCount()==2) {
				//�ж��Ƿ�˫�����
				fimg.setIcon(new ImageIcon(MainJFrame.class.getResource("/img/icon/"+f.getImg()+".PNG")));
				ChatJFrame cj=chats.get(f.getAccount());
				if (cj==null) {
					//��һ����ʾ
				cj=new ChatJFrame(f,u,s);
				cj.setLocationRelativeTo(null);
				cj.setVisible(true);
				//���½����Ĵ��ڷ��õ�map��
				chats.put(f.getAccount(), cj);
				}else {
					//���д���
					cj.setVisible(true);
				}
				
			}
		}
	});
	return fimg;
}
/**
 * �ú���ͷ�񶶶�
 * @param msg
 */
public void tip(SendMsg msg){
	
	//��ʾ�����촰��
	ChatJFrame cj=chats.get(msg.getFrom().getAccount());
	if (cj==null) {
		cj=new ChatJFrame(msg.getFrom(), u, s);
		cj.setLocationRelativeTo(null);
		chats.put(msg.getFrom().getAccount(), cj);
	}
	cj.appendMsg(msg);//���������Ϣ
	if (!cj.isVisible()) {
		//������ڲ��ɼ�����ͷ������
		Component[] img=panel.getComponents();
		System.out.println("������"+img.length);
		for (Component c : img) {
			//�ҵ���Ӧ��ͷ��
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
		setTitle(u.getNickname());//���ñ���Ϊ�û��ǳ�
		initFriends();
		new ServerThread(s,u,MainJFrame.this).start();//�����̼߳������������͵���Ϣ
	}
}
