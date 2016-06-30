package cn.qdu.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.qdu.qq.bis.SysBis;
import cn.qdu.qq.util.DialogUtil;
import cn.qdu.qq.vo.Find;
import cn.qdu.qq.vo.User;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Component;

public class FindJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
   //=======
	private ButtonGroup bg1;
	private User u;//当前登录用户
	private Socket s;
	private SysBis sBis;
	private JTextField nicktextField;
	private JRadioButton rdbtnNewRadioButton2 ;
	private JRadioButton rdbtnNewRadioButton3 ;
	private JRadioButton rdbtnNewRadioButton1  ;
			



	/**
	 * Create the frame.
	 */
	public FindJFrame(Socket s,User u) {
		this.s=s;
		sBis=new SysBis(s);
		this.u=u;
		setTitle("\u67E5\u627E\u597D\u53CB");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 365);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		rdbtnNewRadioButton1 = new JRadioButton("\u7CBE\u786E\u67E5\u627E");
		rdbtnNewRadioButton1.setBackground(Color.WHITE);
		rdbtnNewRadioButton1.setForeground(Color.BLUE);
		rdbtnNewRadioButton1.setFont(new Font("华文楷体", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		rdbtnNewRadioButton3 = new JRadioButton("\u67E5\u627E\u6240\u6709");
		rdbtnNewRadioButton3.setBackground(Color.WHITE);
		rdbtnNewRadioButton3.setForeground(Color.BLUE);
		rdbtnNewRadioButton3.setFont(new Font("华文楷体", Font.PLAIN, 20));
		rdbtnNewRadioButton3.setSelected(true);
		rdbtnNewRadioButton2 = new JRadioButton("\u6A21\u7CCA\u67E5\u627E");
		rdbtnNewRadioButton2.setSelected(false);
		rdbtnNewRadioButton2.setBackground(Color.WHITE);
		rdbtnNewRadioButton2.setForeground(Color.BLUE);
		rdbtnNewRadioButton2.setFont(new Font("华文楷体", Font.PLAIN, 20));
		rdbtnNewRadioButton2.setToolTipText("由于出现难以解决的BUG，该功能尚未实现！");
		{
		bg1 = new ButtonGroup();
		bg1.add(rdbtnNewRadioButton1);
		bg1.add(rdbtnNewRadioButton3);
		bg1.add(rdbtnNewRadioButton2);
		}
		
		JButton btnNewButton = new JButton("\u67E5\u627E");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//精确查找 查找所有
				Find find=new Find();
				if (rdbtnNewRadioButton1.isSelected()) {
					//精确
					System.out.println("启用精确查找");
					find.setType(Find.ONE);
						find.setFaccount(textField.getText().trim());//设置帐号
						find.setFrom(u.getAccount());//将当前用户账号存入
				}else if (rdbtnNewRadioButton2.isSelected())  {
					//昵称模糊查找
					System.out.println("启用模糊查找");
					find.setType(Find.NAME);
					find.setFrom(u.getAccount());
					find.setNickname(nicktextField.getText().trim());
				}else if (rdbtnNewRadioButton3.isSelected()) {
					//所有
					System.out.println("启用全面查找");
					find.setFrom(u.getAccount());
					find.setType(Find.ALL);
				}
				//把查找的信息发送给服务器
				try {
					sBis.find(find);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("华文楷体", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("\u5173\u95ED");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("华文楷体", Font.PLAIN, 20));
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		

		
		JLabel lblNewLabel_1 = new JLabel("\u6635\u79F0\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		nicktextField = new JTextField();
		nicktextField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
							.addComponent(btnNewButton_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(rdbtnNewRadioButton3))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnNewRadioButton1)
								.addComponent(rdbtnNewRadioButton2))
							.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(nicktextField, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(nicktextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addComponent(rdbtnNewRadioButton3)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(44))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(93)
					.addComponent(rdbtnNewRadioButton2)
					.addGap(180))
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {btnNewButton, btnNewButton_1});
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {textField, nicktextField});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNewButton, btnNewButton_1});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {textField, nicktextField});
		contentPane.setLayout(gl_contentPane);
	}
}
