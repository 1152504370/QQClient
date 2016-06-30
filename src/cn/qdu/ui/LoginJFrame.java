package cn.qdu.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import cn.qdu.qq.bis.SysBis;
import cn.qdu.qq.util.DialogUtil;
import cn.qdu.qq.util.PropertiesUtil;
import cn.qdu.qq.vo.User;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class LoginJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField1;
	private Socket s;//连接服务器的Socket
	private SysBis sBis;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
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
	 * 登录方法
	 */
	public void login(){
		String name=textField1.getText().trim();
		String password=new String(passwordField.getPassword()).trim();
//	使用对象序列化
//	1.类必须一致（包名加类名）
//	2.实现序列化接口
		User u=new User(name,password);
		try {
			User u1=sBis.login(u);
			if(u1==null){
				//失败
				DialogUtil.showAlarm("用户名或密码错误！");
			}else {
				//成功
				System.out.println("好友数量:"+u1.getFriends().size());
//				DialogUtil.showAlarm("登录成功！");
				dispose();
				MainJFrame mj=new MainJFrame(u1,s);
				mj.setLocationRelativeTo(null);
				mj.setVisible(true);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			DialogUtil.showAlarm("服务器异常！");
		}catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}	
	}

	/**
	 * Create the frame.
	 */
	public LoginJFrame(){
		setTitle("\u767B\u5F55QQ");
		unnit();
	}
	private  void unnit() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					s.close();
					dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 412);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(null);
		lblNewLabel.setBackground(Color.CYAN);
		lblNewLabel.setIcon(new ImageIcon(LoginJFrame.class.getResource("/img/login/3.PNG")));
		
		JLabel lblNewLabel_1 = new JLabel("\r\n\u8D26\u53F7\uFF1A");
		lblNewLabel_1.setIcon(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setIcon(null);
		
		textField1 = new JTextField();
		textField1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u6CE8\u518C\u8D26\u53F7");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			/**
			 * 注册
			 * @param e
			 */
			
			public void mouseClicked(MouseEvent e) {
				setVisible(false);//登录窗口消失
				RegeditJFrame rf=new RegeditJFrame(LoginJFrame.this,s);
				rf.setLocationRelativeTo(null);
				rf.setVisible(true);
			}
		});
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setIcon(null);
		
		JLabel lblNewLabel_5 = new JLabel("\u53D6\u56DE\u5BC6\u7801");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setIcon(null);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.setFont(new Font("华文楷体", Font.PLAIN, 20));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * 登录
			 */
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5728\u7EBF", "\u9690\u8EAB", "\u79BB\u5F00"}));
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(20)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(chckbxNewCheckBox)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(28)
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
						.addComponent(chckbxNewCheckBox_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblNewLabel_1, lblNewLabel_2});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {textField1, btnNewButton, passwordField});
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);//设置窗口居中
		this.setVisible(true);//设置窗口显示
		try {
			s=new Socket(PropertiesUtil.readPro("ip"),
					Integer.parseInt(PropertiesUtil.readPro("port")));
			sBis=new SysBis(s);
		} catch (IOException e1) {
			//弹出对话框提示用户
			DialogUtil.showAlarm("连接服务器失败！"); 
			e1.printStackTrace();
		}
	}
}
