package com.gpg.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JLabel title;
	private JLabel select;
	
	private JButton login;
	private JButton register;
	private JButton exit;
	private Font textFont = new Font("微软雅黑", 0, 14);

	public MainScreen() {
		initComponents();
	}

	private void initComponents() {
		add(getJPanel0(), BorderLayout.CENTER);
		setSize(320, 231);
	}

	private JButton getJButton2() {
		if (exit == null) {
			exit = new JButton();
			exit.setText("退出");
			exit.setFont(textFont);
			exit.addMouseListener(new MouseAdapter() {
	
				public void mouseReleased(MouseEvent event) {
					exitMouseMouseReleased(event);
				}
			});
		}
		return exit;
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

	private JButton getJButton0() {
		if (login == null) {
			login = new JButton();
			login.setFont(textFont);
			login.setText("登录");
			login.addMouseListener(new MouseAdapter() {
	
				public void mouseReleased(MouseEvent event) {
					loginMouseMouseReleased(event);
				}
			});
		}
		return login;
	}


	private JLabel getJLabel1() {
		if (select == null) {
			select = new JLabel();
			select.setFont(textFont);
			select.setText("请选择：");
		}
		return select;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel0(), new Constraints(new Leading(100, 10, 10), new Leading(34, 10, 10)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(49, 10, 10), new Leading(102, 10, 10)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(39, 10, 10), new Leading(161, 10, 10)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(132, 10, 10), new Leading(161, 12, 12)));
			jPanel0.add(getJButton2(), new Constraints(new Leading(219, 10, 10), new Leading(161, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel0() {
		if (title == null) {
			title = new JLabel();
			title.setFont(new Font("微软雅黑", 0, 17));
			title.setText("欢迎使用该系统");
		}
		return title;
	}

	private void loginMouseMouseReleased(MouseEvent event) {
		Login login = new Login();
		login .setVisible(true);
		this.setVisible(false);
	}

	private void exitMouseMouseReleased(MouseEvent event) {
		System.exit(0);
	}

	private void registerMouseMouseReleased(MouseEvent event) {
			Register register = new Register();
			this.setVisible(false);
			register.setVisible(true);
	}

}
