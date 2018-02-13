package com.gpg.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.gpg.server.SocketListenner;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ChatServer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JLabel jLabel0;
	private Font titleFont = new Font("微软雅黑", 0, 17);
	private Font labelFont = new Font("微软雅黑", 0, 14);
	private JLabel jLabel1;
	private JTextField port;
	private JButton openBtn;
	private JList<String> jList0;
	private JScrollPane jScrollPane0;
	private JTextArea connnTextArea;
	private JScrollPane jScrollPane1;
	public DefaultListModel<String> listModel;
	private JTextArea msgTextArea;
	private JScrollPane jScrollPane2;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;

	public ChatServer() {
		initComponents();
	}

	private void initComponents() {
		add(getJPanel0(), BorderLayout.CENTER);
		setSize(320, 407);
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setFont(titleFont);
			jLabel4.setText("聊天信息");
		}
		return jLabel4;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setFont(labelFont);
			jLabel3.setText("连接信息");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setFont(labelFont);
			jLabel2.setText("用户列表");
		}
		return jLabel2;
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTextArea1());
		}
		return jScrollPane2;
	}

	public JTextArea getJTextArea1() {
		if (msgTextArea == null) {
			msgTextArea = new JTextArea();
			msgTextArea.setText("");
		}
		return msgTextArea;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTextArea0());
		}
		return jScrollPane1;
	}

	public JTextArea getJTextArea0() {
		if (connnTextArea == null) {
			connnTextArea = new JTextArea();
			connnTextArea.setCaretPosition(connnTextArea.getText().length());
			connnTextArea.setFont(labelFont);
		}
		return connnTextArea;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJList0());
		}
		return jScrollPane0;
	}

	public JList<String> getJList0() {
		if (jList0 == null) {
			jList0 = new JList<String>();
			listModel = new DefaultListModel<>();
			listModel.addElement("");
			jList0.setModel(listModel);
		}
		return jList0;
	}

	private JButton getJButton0() {
		if (openBtn == null) {
			openBtn = new JButton();
			openBtn.setFont(labelFont);
			openBtn.setText("开启服务端");
			openBtn.addMouseListener(new MouseAdapter() {

				public void mouseReleased(MouseEvent event) {
					openBtnMouseMouseReleased(event);
				}
			});
		}
		return openBtn;
	}

	private JTextField getJTextField0() {
		if (port == null) {
			port = new JTextField();
			port.setText("8888");
		}
		return port;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setFont(labelFont);
			jLabel1.setText("端口号:");
		}
		return jLabel1;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJLabel0(), new Constraints(new Leading(137, 10, 10), new Leading(12, 12, 12)));
			jPanel1.add(getJButton0(), new Constraints(new Leading(180, 10, 10), new Leading(41, 12, 12)));
			jPanel1.add(getJTextField0(), new Constraints(new Leading(99, 65, 10, 10), new Leading(43, 24, 12, 12)));
			jPanel1.add(getJLabel1(), new Constraints(new Leading(40, 10, 10), new Leading(43, 23, 12, 12)));
			jPanel1.add(getJScrollPane2(), new Constraints(new Leading(32, 265, 10, 10), new Leading(294, 95, 10, 10)));
			jPanel1.add(getJScrollPane0(), new Constraints(new Leading(31, 71, 10, 10), new Leading(105, 135, 12, 12)));
			jPanel1.add(getJScrollPane1(),
					new Constraints(new Leading(142, 154, 10, 10), new Leading(105, 135, 12, 12)));
			jPanel1.add(getJLabel4(), new Constraints(new Leading(137, 12, 12), new Leading(263, 10, 10)));
			jPanel1.add(getJLabel2(), new Constraints(new Leading(38, 12, 12), new Leading(82, 12, 12)));
			jPanel1.add(getJLabel3(), new Constraints(new Leading(191, 12, 12), new Leading(82, 12, 12)));
		}
		return jPanel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(titleFont);
			jLabel0.setText("服务端");
		}
		return jLabel0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new BorderLayout());
			jPanel0.add(getJPanel1(), BorderLayout.CENTER);
		}
		return jPanel0;
	}

	private void openBtnMouseMouseReleased(MouseEvent event) {
		if (port.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "端口号不能为空！");
		} else {
			int iPort = Integer.parseInt(port.getText());
			SocketListenner socketListenner = new SocketListenner(iPort);
			socketListenner.setChatServer(this);
			socketListenner.start();
			JOptionPane.showMessageDialog(null, "服务端已开启");
		}
	}

}
