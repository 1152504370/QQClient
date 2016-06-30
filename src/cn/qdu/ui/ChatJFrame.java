package cn.qdu.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.qdu.qq.bis.UserBis;
import cn.qdu.qq.vo.SendMsg;
import cn.qdu.qq.vo.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import javax.swing.JScrollPane;
import java.awt.Component;

public class ChatJFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea_1;
	private JTextArea textArea ;
	private User f;//好友
	private User u;
	private UserBis uBis;
	

	public void appendMsg(SendMsg msg){
		textArea.append(msg.toString());
	}

	/**
	 * Create the frame.
	 */
	public ChatJFrame(User f,User u,Socket s) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		this.u=u;
		this.f=f;
		uBis=new UserBis(s);
		setTitle("与"+f.getNickname()+"的对话");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 561, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ChatJFrame.class.getResource("/img/chat/female.png")));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ChatJFrame.class.getResource("/img/chat/male.png")));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ChatJFrame.class.getResource("/img/chat/1.png")));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(ChatJFrame.class.getResource("/img/chat/folder.png")));
		lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JButton btnNewButton = new JButton("\u53D1\u9001");
		/**
		 * 发送聊天消息
		 */
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SendMsg msg=new SendMsg();
				msg.setFrom(u);
				msg.setTo(f);
				msg.setMsg(textArea_1.getText());
				msg.setTime(new Date());
				try {
					uBis.sendMsg(msg);
					textArea .append(msg.toString());
					textArea_1.setText("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u5173\u95ED");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		textArea_1 = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addGap(103)
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
									.addComponent(btnNewButton_1)
									.addGap(18))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(18)
							.addComponent(lblNewLabel_1)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(28)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnNewButton)
									.addComponent(btnNewButton_1))
								.addComponent(lblNewLabel_3))
							.addGap(25))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addContainerGap())))
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {textArea_1, scrollPane});
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		contentPane.setLayout(gl_contentPane);
	}
}
