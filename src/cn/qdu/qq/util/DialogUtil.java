package cn.qdu.qq.util;

import javax.swing.JOptionPane;

public class DialogUtil {
public static void showAlarm(String str){
	JOptionPane.showMessageDialog(null, str, "ϵͳ����", JOptionPane.WARNING_MESSAGE);
}
public static void showInfo(String str){
	JOptionPane.showMessageDialog(null, str, "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
}
public static boolean showconfirm(String str){
	int i=JOptionPane.showConfirmDialog(null, str,"ϵͳȷ��", JOptionPane.YES_NO_OPTION);
	if (i==JOptionPane.OK_OPTION) {
		return true;
	}else {
		return false;
	}
}

}
