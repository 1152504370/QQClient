package cn.qdu.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.qdu.qq.vo.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoJFrame extends JFrame {

	private JPanel contentPane;
	//===================
	private User u;
	private JLabel lblNewLabel ;
	private JLabel label ;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel lblNewLabel_2;

/**
 * 初始化所有用户显示信息
 */
	private void initInfo(){
		label1.setText(u.getAccount());
		label2.setText(u.getNickname());
		label3.setText(String.valueOf(u.getAge()));
		label4.setText(u.getEmail());
		lblNewLabel_2.setIcon(new ImageIcon(InfoJFrame.class.getResource("/img/icon/"+u.getImg()+".png")));
	}

	/**
	 * Create the frame.
	 */
	public InfoJFrame(User u) {
		setTitle("\u8BE6\u7EC6\u4FE1\u606F");
		this.u=u;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 471, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblNewLabel = new JLabel("\u5E10\u53F7\uFF1A");
		
		label = new JLabel("\u6635\u79F0\uFF1A");
		
		label_1 = new JLabel("\u5E74\u9F84\uFF1A");
		
		label_2 = new JLabel("\u90AE\u7BB1\uFF1A");
		
		label_3 = new JLabel("\u5934\u50CF\uFF1A");
		
		label1 = new JLabel("");
		
		label2 = new JLabel("");
		
		label3 = new JLabel("");
		
		label4 = new JLabel("");
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(InfoJFrame.class.getResource("/img/icon/1.png")));
		
		JButton btnNewButton = new JButton("\u5173\u95ED");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("华文楷体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(50)
							.addComponent(btnNewButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel)
									.addComponent(label)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label1, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
									.addGap(41)
									.addComponent(label_3))
								.addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(413, Short.MAX_VALUE)
							.addComponent(lblNewLabel_2)))
					.addGap(91))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(label1)
						.addComponent(label_3))
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(label2))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(label3))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(label4)
						.addComponent(btnNewButton))
					.addGap(120))
		);
		contentPane.setLayout(gl_contentPane);
		initInfo();
	}
}
