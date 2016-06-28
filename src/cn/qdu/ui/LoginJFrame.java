package cn.qdu.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import cn.qdu.qq.bis.SysBis;
import cn.qdu.qq.util.DialogUtil;
import cn.qdu.qq.util.PropertiesUtil;
import cn.qdu.qq.vo.Users;
import javax.swing.JPasswordField;

public class LoginJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField1;
	private Socket s;//���ӷ�������Socket
	private SysBis sBis;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginJFrame frame = new LoginJFrame();
//					frame.setLocationRelativeTo(null)
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 412);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.disabledShadow"));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(null);
		lblNewLabel.setBackground(new Color(0, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(LoginJFrame.class.getResource("/img/login/3.PNG")));
		
		JLabel lblNewLabel_1 = new JLabel("\r\n\u8D26\u53F7\uFF1A");
		lblNewLabel_1.setIcon(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setIcon(null);
		
		textField1 = new JTextField();
		textField1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u6CE8\u518C\u8D26\u53F7");
		lblNewLabel_4.setForeground(new Color(0, 0, 255));
		lblNewLabel_4.setIcon(null);
		
		JLabel lblNewLabel_5 = new JLabel("\u53D6\u56DE\u5BC6\u7801");
		lblNewLabel_5.setForeground(new Color(0, 0, 255));
		lblNewLabel_5.setIcon(null);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * ��¼
			 */
			public void actionPerformed(ActionEvent e) {
				String name=textField1.getText().trim();
				String password=new String(passwordField.getPassword()).trim();
//			ʹ�ö������л�
//			1.�����һ�£�������������
//			2.ʵ�����л��ӿ�
				Users u=new Users(name,password);
				try {
					Users u1=sBis.login(u);
					if(u1==null){
						//ʧ��
						DialogUtil.showAlarm("�û������������");
					}else {
						//�ɹ�
						System.out.println("��������:"+u1.getFriends().size());
						DialogUtil.showAlarm("��¼�ɹ���");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					DialogUtil.showAlarm("�������쳣��");
				}catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
		
		passwordField = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGap(20)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(chckbxNewCheckBox)
											.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
											.addComponent(chckbxNewCheckBox_1))
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_5))
							.addGap(33)))
					.addGap(58))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxNewCheckBox)
						.addComponent(chckbxNewCheckBox_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNewButton, passwordField, textField1});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblNewLabel_1, lblNewLabel_2});
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);//���ô��ھ���
		this.setVisible(true);//���ô�����ʾ
		try {
			s=new Socket(PropertiesUtil.readPro("ip"),
					Integer.parseInt(PropertiesUtil.readPro("port")));
			sBis=new SysBis(s);
		} catch (IOException e1) {
			//�����Ի�����ʾ�û�
			DialogUtil.showAlarm("���ӷ�����ʧ�ܣ�"); 
			e1.printStackTrace();
		}
	}
}
