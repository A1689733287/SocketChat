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
import javax.swing.SwingUtilities;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.gpg.client.ChatUtil;
import com.gpg.entity.User;
import com.gpg.service.UserService;
import com.gpg.service.impl.UserServiceImpl;
import com.gpg.util.RSAUtil;

//VS4E -- DO NOT REMOVE THIS LINE!
public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JLabel title;
	private JLabel userName;
	private JLabel userPwd;
	private JTextField userNameText;
	private JButton login;
	private JButton returnMain;
	private UserService userService = new UserServiceImpl();
	private Font textFont = new Font("微软雅黑", 0, 14);
	private JPasswordField userPwdText;

	public Login() {
		initComponents();
	}

	private void initComponents() {
		add(getJPanel0(), BorderLayout.CENTER);
		setSize(320, 240);
	}

	private JPasswordField getJPasswordField0() {
		if (userPwdText == null) {
			userPwdText = new JPasswordField();
			userPwdText.setText("");
			userPwdText.setEchoChar('•');
		}
		return userPwdText;
	}

	private JButton getJButton1() {
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

	private JButton getJButton0() {
		if (login == null) {
			login = new JButton();
			login.setText("登录");
			login.setFont(textFont);
			login.addMouseListener(new MouseAdapter() {

				public void mouseReleased(MouseEvent event) {
					loginMouseMouseReleased(event);
				}
			});
		}
		return login;
	}

	private JTextField getJTextField0() {
		if (userNameText == null) {
			userNameText = new JTextField();
		}
		return userNameText;
	}

	private JLabel getJLabel2() {
		if (userPwd == null) {
			userPwd = new JLabel();
			userPwd.setFont(new Font("微软雅黑", 0, 15));
			userPwd.setText("密码：");
		}
		return userPwd;
	}

	private JLabel getJLabel1() {
		if (userName == null) {
			userName = new JLabel();
			userName.setFont(new Font("微软雅黑", 0, 15));
			userName.setText("用户名：");
		}
		return userName;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel0(), new Constraints(new Leading(126, 71, 10, 10), new Leading(31, 10, 10)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(48, 10, 10), new Leading(81, 10, 10)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(48, 12, 12), new Leading(123, 10, 10)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(126, 135, 10, 10), new Leading(79, 12, 12)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(48, 12, 12), new Leading(181, 10, 10)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(174, 10, 10), new Leading(181, 12, 12)));
			jPanel0.add(getJPasswordField0(), new Constraints(new Leading(126, 134, 12, 12), new Leading(123, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel0() {
		if (title == null) {
			title = new JLabel();
			title.setFont(new Font("微软雅黑", 0, 16));
			title.setText("登录页面");
		}
		return title;
	}

	private void returnMainMouseMouseReleased(MouseEvent event) {
		MainScreen main = new MainScreen();
		this.setVisible(false);
		main.setVisible(true);
	}

	private void loginMouseMouseReleased(MouseEvent event) {
		String userName = userNameText.getText();
		@SuppressWarnings("deprecation")
		String userPwd = RSAUtil.encrypt(userPwdText.getText());
		if (!(userName.trim().isEmpty() || userPwd.trim().isEmpty())) {
				User user = new User(0, userName, userPwd);
				if (check(user)) {
					this.setVisible(false);
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							ChatClient client = new ChatClient();
							client.getJLabel3().setText(userName);
							client.setVisible(true);
							ChatUtil.getInstance().setWindow(client);
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码不正确！");
				}
		} else {
			JOptionPane.showMessageDialog(null, "用户名或密码不能为空！");
		}

	}

	private boolean check(User user) {
		User use = userService.query(user);
		if (use == null) {
			return false;
		} else {
			return true;
		}
	}
}
