package com.gpg.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.gpg.entity.User;
import com.gpg.service.UserService;
import com.gpg.service.impl.UserServiceImpl;
import com.gpg.util.MyUtil;
import com.gpg.util.RSAUtil;

//VS4E -- DO NOT REMOVE THIS LINE!
public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JLabel jLabel0;
	private JLabel userName;
	private JLabel userPwd_0;
	private JLabel userPwd_1;
	private JButton register;
	private JButton returnMain;
	private JTextField userNameText;
	private JPasswordField userPwdText_1;
	private JPasswordField userPwdText;
	private UserService userService = new UserServiceImpl();
	private Font textFont = new Font("微软雅黑", 0, 14);

	public Register() {
		initComponents();
	}

	private void initComponents() {
		add(getJPanel0(), BorderLayout.CENTER);
		setSize(320, 240);
	}

	private JPasswordField getJPasswordField1() {
		if (userPwdText == null) {
			userPwdText = new JPasswordField();
		}
		return userPwdText;
	}

	private JPasswordField getJPasswordField2() {
		if (userPwdText_1 == null) {
			userPwdText_1 = new JPasswordField();
		}
		return userPwdText_1;
	}

	private JTextField getJTextField0() {
		if (userNameText == null) {
			userNameText = new JTextField();
		}
		return userNameText;
	}

	private JButton getJButton0() {
		if (returnMain == null) {
			returnMain = new JButton();
			returnMain.setFont(textFont);
			returnMain.setText("返回主界面");
			returnMain.addMouseListener(new MouseAdapter() {

				public void mouseReleased(MouseEvent event) {
					returnMainMouseMouseReleased(event);
				}
			});
		}
		return returnMain;
	}

	private JButton getJButton1() {
		if (register == null) {
			register = new JButton();
			register.setFont(textFont);
			register.setText("注册");
			register.addMouseListener(new MouseAdapter() {

				public void mouseReleased(MouseEvent event) {
					registerMouseMouseReleased(event);
				}
			});
		}
		return register;
	}

	private JLabel getJLabel3() {
		if (userPwd_1 == null) {
			userPwd_1 = new JLabel();
			userPwd_1.setFont(textFont);
			userPwd_1.setText("确认密码：");
		}
		return userPwd_1;
	}

	private JLabel getJLabel2() {
		if (userPwd_0 == null) {
			userPwd_0 = new JLabel();
			userPwd_0.setFont(textFont);
			userPwd_0.setText("密码：");
		}
		return userPwd_0;
	}

	private JLabel getJLabel1() {
		if (userName == null) {
			userName = new JLabel();
			userName.setFont(textFont);
			userName.setText("用户名：");
		}
		return userName;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel0(), new Constraints(new Leading(122, 10, 10), new Leading(20, 10, 10)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(41, 12, 12), new Leading(68, 10, 10)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(41, 12, 12), new Leading(99, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(41, 12, 12), new Leading(133, 10, 10)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(125, 129, 10, 10), new Leading(68, 12, 12)));
			jPanel0.add(getJPasswordField2(), new Constraints(new Leading(125, 128, 12, 12), new Leading(132, 12, 12)));
			jPanel0.add(getJPasswordField1(), new Constraints(new Leading(125, 130, 12, 12), new Leading(98, 12, 12)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(62, 10, 10), new Leading(181, 10, 10)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(169, 10, 10), new Leading(181, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("微软雅黑", 0, 17));
			jLabel0.setText("注册界面");
		}
		return jLabel0;
	}

	private void registerMouseMouseReleased(MouseEvent event) {
		String userName = userNameText.getText();
		String userPwd_1 = RSAUtil.encrypt(userPwdText.getText());
		String userPwd_2 = RSAUtil.encrypt(userPwdText_1.getText());
		if (!(userName.trim().isEmpty() || userPwd_1.trim().isEmpty() || userPwd_2.trim().isEmpty())) {
			if (MyUtil.checkPwd(userPwd_1, userPwd_2)) {
				User user = new User(0, userName, userPwd_1);
				if (userService.register(user) > 0) {
					MainScreen main = new MainScreen();
					this.setVisible(false);
					main.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "注册失败!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "两次密码不一致！");
			}
		} else {
			JOptionPane.showMessageDialog(null, "用户名或者密码不能为空！");
		}
	}

	private void returnMainMouseMouseReleased(MouseEvent event) {
		MainScreen main = new MainScreen();
		this.setVisible(false);
		main.setVisible(true);
	}

}
