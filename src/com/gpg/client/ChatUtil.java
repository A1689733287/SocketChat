package com.gpg.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.gpg.ui.ChatClient;

public class ChatUtil {

	private ChatClient window;
	private PrintStream ps;
	private BufferedReader br;
	private boolean isConnect = false;

	private final static ChatUtil chat = new ChatUtil();

	private ChatUtil() {
	}

	public static ChatUtil getInstance() {
		return chat;
	}

	public void setWindow(ChatClient window) {
		this.window = window;
	}

	public ChatClient getWindow() {
		return window;
	}

	public void connect(String ip, int port) {
		isConnect = true;
		JOptionPane.showMessageDialog(null, "已建立连接！");
		new Thread() {
			public void run() {
				try {
					@SuppressWarnings("resource")
					Socket socket = new Socket(ip, port);
					br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
					ps = new PrintStream(socket.getOutputStream());
					send(window.getJLabel3().getText() +"#" + socket.getLocalAddress());
					String line = null;
					while ((line = br.readLine()) != null) {
						window.getJTextArea0().append(line + "\r\n");
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "服务端以断开,请重新连接！");
					isConnect = false;
				}
			}
		}.start();
	}

	public void send(String msg) {
		if (ps != null) {
			ps.println(msg);
		} else {
			JOptionPane.showMessageDialog(null, "当前连接已经断开！");
		}
	}

	public boolean isConnect() {
		return isConnect;
	}

	public void setConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}
}
