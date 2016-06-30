package cn.qdu.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import cn.qdu.qq.bis.UserBis;
import cn.qdu.qq.util.DialogUtil;
import cn.qdu.qq.vo.AddFriendsMsg;
import cn.qdu.qq.vo.User;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;

public class FindRSJFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
//==========
	private List<User> ulist;
	private User u;//当前登录用户
	private UserBis uBis;
	private Socket s;
	
	/**
	 * 把list中的信息转换为String[][]格式
	 * @return
	 */
	
	private String[][] getForTable(){
		String strs[][]=new String[ulist.size()][2];
		for (int i = 0; i <ulist.size(); i++) {
			strs[i][0]=ulist.get(i).getAccount();
			strs[i][1]=ulist.get(i).getNickname();
		}
		return strs;
		
	}

	/**
	 * Create the frame.
	 */
	public FindRSJFrame(List<User> ulist,User u,Socket s) {
		this.s=s;
		this.ulist=ulist;
		this.u=u;
		uBis=new UserBis(this.s);
		setTitle("\u4FE1\u606F\u5217\u8868");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(400, 500));
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2\u4FE1\u606F");
		/**
		 * 查看信息
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=table.getSelectedRow();
				if (index!=-1) {
					//选中
					InfoJFrame info=new InfoJFrame(ulist.get(index));
					info.setLocationRelativeTo(null);
					info.setVisible(true);
				}else {
					//没选中
					DialogUtil.showAlarm("请选择一个用户！");
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u52A0\u4E3A\u597D\u53CB");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=table.getSelectedRow();
				if (index!=-1) {
					//选中
					User f=ulist.get(index);//要添加的好友
					AddFriendsMsg msg=new AddFriendsMsg();
					msg.setFrom(u);
					msg.setTo(ulist.get(index));
					try {
						uBis.addFriends(msg);//发送添加好友请求
						DialogUtil.showInfo("已向对方发送好友请求，请等待回应。");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else {
					//没选中
					DialogUtil.showAlarm("请选择一个用户！");
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u5173\u95ED");
		btnNewButton_2.setToolTipText("点击关闭当前窗口！");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 549, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(8, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)
							.addGap(42)
							.addComponent(btnNewButton_2)
							.addGap(32))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {btnNewButton, btnNewButton_1, btnNewButton_2});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNewButton, btnNewButton_1, btnNewButton_2});
		
		table = new JTable();
		TableModel tableModel=new DefaultTableModel(
				getForTable(),
				new String[] {
					"帐号", "昵称"
				}
			);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
