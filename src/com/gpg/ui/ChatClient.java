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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.gpg.client.ChatUtil;

public class ChatClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JLabel title;
	private JLabel jLabel1;
	private JTextField ipAddr;
	private JLabel jLabel2;
	private JTextField port;
	private JButton conBtn;
	private JTextField msgText;
	private JButton sendBtn;
	private JLabel userName;
	private Font titleFont = new Font("微软雅黑", 0, 17);
	private Font textFont = new Font("微软雅黑", 0, 14);
	private JButton exitBtn;
	private JTextArea msgTextArea;
	private JScrollPane jScrollPane1;

	public ChatClient() {
		initComponents();
	}

	private void initComponents() {
		add(getJPanel0(), BorderLayout.CENTER);
		setSize(485, 358);
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTextArea0());
		}
		return jScrollPane1;
	}

	public JTextArea getJTextArea0() {
		if (msgTextArea == null) {
			msgTextArea = new JTextArea();
			msgTextArea.setEditable(false);
			msgTextArea.setCaretPosition(msgTextArea.getText().length());
		}
		return msgTextArea;
	}

	private JButton getJButton2() {
		if (exitBtn == null) {
			exitBtn = new JButton();
			exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			exitBtn.setText("退出");
			exitBtn.addMouseListener(new MouseAdapter() {

				public void mouseReleased(MouseEvent event) {
					exitBtnMouseMouseReleased(event);
				}
			});
		}
		return exitBtn;
	}

	public JLabel getJLabel3() {
		if (userName == null) {
			userName = new JLabel();
			userName.setText(":");
		}
		return userName;
	}

	private JButton getJButton1() {
		if (sendBtn == null) {
			sendBtn = new JButton();
			sendBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			sendBtn.setText("发送");
			sendBtn.addMouseListener(new MouseAdapter() {

				public void mouseReleased(MouseEvent event) {
					sendBtnMouseMouseReleased(event);
				}
			});
		}
		return sendBtn;
	}

	private JTextField getJTextField2() {
		if (msgText == null) {
			msgText = new JTextField();
		}
		return msgText;
	}

	private JButton getJButton0() {
		if (conBtn == null) {
			conBtn = new JButton();
			conBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			conBtn.setText("连接服务端");
			conBtn.addMouseListener(new MouseAdapter() {

				public void mouseReleased(MouseEvent event) {
					conBtnMouseMouseReleased(event);
				}
			});
		}
		return conBtn;
	}

	private JTextField getJTextField1() {
		if (port == null) {
			port = new JTextField();
			port.setText("8888");
		}
		return port;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setFont(textFont);
			jLabel2.setText("端口号:");
		}
		return jLabel2;
	}

	private JTextField getJTextField0() {
		if (ipAddr == null) {
			ipAddr = new JTextField();
			ipAddr.setText("127.0.0.1");
		}
		return ipAddr;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setFont(textFont);
			jLabel1.setText("IP地址:");
		}
		return jLabel1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel1(), new Constraints(new Leading(33, 10, 10), new Leading(47, 22, 12, 12)));
			jPanel0.add(getJTextField2(), new Constraints(new Leading(32, 325, 12, 12), new Leading(309, 35, 12, 12)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(370, 75, 12, 12), new Leading(309, 35, 12, 12)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(143, 10, 10), new Leading(6, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(269, 57, 12, 12), new Leading(11, 12, 12)));
			jPanel0.add(getJButton2(), new Constraints(new Leading(384, 61, 12, 12), new Leading(7, 10, 10)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(201, 12, 12), new Leading(44, 25, 12, 12)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(342, 105, 12, 12), new Leading(44, 12, 12)));
			jPanel0.add(getJScrollPane1(), new Constraints(new Leading(33, 412, 12, 12), new Leading(83, 214, 12, 12)));
			jPanel0.add(getJTextField1(), new Constraints(new Leading(256, 68, 10, 10), new Leading(43, 30, 12, 12)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(78, 101, 10, 10), new Leading(43, 30, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel0() {
		if (title == null) {
			title = new JLabel();
			title.setFont(titleFont);
			title.setText("欢迎来到聊天室");
		}
		return title;
	}

	private void exitBtnMouseMouseReleased(MouseEvent event) {
		MainScreen mainScreen = new MainScreen();
		this.setVisible(false);
		mainScreen.setVisible(true);
	}

	private void conBtnMouseMouseReleased(MouseEvent event) {
		if (ChatUtil.getInstance().isConnect()) {
			JOptionPane.showMessageDialog(null, "不能重复进行连接！");
		} else {
			String ip = ipAddr.getText();
			int pt = Integer.parseInt(port.getText());
			ChatUtil.getInstance().connect(ip, pt);
		}
	}

	private void sendBtnMouseMouseReleased(MouseEvent event) {
		if (!msgText.getText().isEmpty()) {
			if (ChatUtil.getInstance().isConnect()) {
				ChatUtil.getInstance().send(userName.getText() + ":" + msgText.getText());
				msgTextArea.append("我说:" + msgText.getText() + "\r\n");
				msgText.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "请连接服务端！");
			}
		} else {
			JOptionPane.showMessageDialog(null, "信息不能为空！");
		}
	}

}
