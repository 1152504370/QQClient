package cn.qdu.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cn.qdu.qq.bis.SysBis;
import cn.qdu.qq.util.DialogUtil;
import cn.qdu.qq.vo.RegeditRs;
import cn.qdu.qq.vo.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegeditJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nametextField;
	private JPasswordField passwordField;
	private JTextField agetextField;
	private JTextField emailtextField;
	private JLabel lblNewLabel_4;
	private JComboBox<String> comboBox;
	private JLabel label;
	//======================
	private LoginJFrame lf;
	private SysBis sBis;

	
public void zhuce(){
	String nickname=nametextField.getText().trim();
	@SuppressWarnings("deprecation")
	String password=passwordField.getText().trim();
	String age=agetextField.getText().trim();
	String email=emailtextField.getText().trim();
    String img=String.valueOf(comboBox.getSelectedIndex()+1);
    if (nickname.length()>8||nickname.length()<1) {
		DialogUtil.showAlarm("昵称长度不能超过8位！");
	}else {
		if (password.length()<4||password.length()>10) {
			DialogUtil.showAlarm("密码长度应处于4-10！");
		}else {
			if (!age.matches("[0-9]+")) {
				DialogUtil.showAlarm("年龄必须为数字！");
			}else {
				if (Integer.parseInt(age)<=0||Integer.parseInt(age)>120) {
					DialogUtil.showAlarm("年龄超出有效范围（0-120）！");
			}else {
				if(email.indexOf('@')<1||email.indexOf('.')==(email.length()-1)||(email.indexOf('.')-email.indexOf('@'))<2){
//				if (!(email.contains("@")&&email.contains(".")&&(email.indexOf("@")<email.indexOf('.'))&&(!email.endsWith(".")))) {
					DialogUtil.showAlarm("邮箱格式无效！（标准格式xxx@xxx.xxx）");
				}else {
				    System.out.println(nickname+" "+email);
				    User u= new User();
				    u.setNickname(nickname);
				    u.setPassword(password);
				    u.setAge(Integer.parseInt(age));
				    u.setEmail(email);
				    u.setImg(img);//1,2,3,4
				    try {
						RegeditRs rs=sBis.regedit(u);
						if (rs.isRs()) {
							//注册成功
							dispose();//销毁注册窗口
							DialogUtil.showInfo(rs.getMsg());
							lf.setVisible(true);
						}else {
							//注册失败
							DialogUtil.showAlarm(rs.getMsg());
						}
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		}
	}

}
}

	/**
	 * Create the frame.
	 */
	public RegeditJFrame(LoginJFrame lf,Socket s) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				lf.setVisible(true);//登录界面显示
				dispose();//销毁
			}
		});
		sBis=new SysBis(s);
		this.lf=lf;
		setForeground(Color.BLUE);
		setFont(new Font("华文楷体", Font.PLAIN, 20));
		setTitle("\u6CE8\u518C\u8D26\u53F7");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 571, 352);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLUE);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		label = new JLabel("");
		
		JLabel lblNewLabel = new JLabel("\u6635\u79F0\uFF1A");
		lblNewLabel.setFont(new Font("华文楷体", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("华文楷体", Font.PLAIN, 20));
		
		nametextField = new JTextField();
		nametextField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		JLabel lblNewLabel_2 = new JLabel("\u5E74\u9F84\uFF1A");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("华文楷体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_3 = new JLabel("\u90AE\u7BB1\uFF1A");
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setFont(new Font("华文楷体", Font.PLAIN, 20));
		
		agetextField = new JTextField();
		agetextField.setColumns(10);
		
		emailtextField = new JTextField();
		emailtextField.setColumns(10);
		
		lblNewLabel_4 = new JLabel("\u5934\u50CF\uFF1A");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("华文楷体", Font.PLAIN, 20));
		
		comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int index=comboBox.getSelectedIndex()+1;
				System.out.println(index);
				label.setIcon(new ImageIcon(RegeditJFrame.class.getResource("/img/icon/"+index+".png")));
			}
		});
		comboBox.addItem("笑脸");
		comboBox.addItem("花朵");
		comboBox.addItem("笔墨");
		comboBox.addItem("脸谱");
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		/**
		 * 注册按钮
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zhuce();
			
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("华文楷体", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("\u5173\u95ED");
		/**
		 * 关闭注册窗口
		 */
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lf.setVisible(true);//登录界面显示
				dispose();//销毁
			}
		});
		btnNewButton_1.setFont(new Font("华文楷体", Font.PLAIN, 20));
		btnNewButton_1.setForeground(Color.RED);
		
		label.setIcon(new ImageIcon(RegeditJFrame.class.getResource("/img/icon/1.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(emailtextField, 166, 166, 166)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nametextField, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(passwordField, 166, 166, 166))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(agetextField, 158, 158, 158)))
							.addGap(49)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNewButton_1))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(label)
										.addComponent(lblNewLabel_4))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(nametextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_4))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(agetextField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
							.addGap(29))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(emailtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(61))
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {nametextField, passwordField, agetextField, emailtextField});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {nametextField, passwordField, agetextField, emailtextField});
		contentPane.setLayout(gl_contentPane);
	}
	
}
