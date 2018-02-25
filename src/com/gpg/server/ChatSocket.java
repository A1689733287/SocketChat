package com.gpg.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.gpg.ui.ChatServer;

public class ChatSocket extends Thread {
	private Socket socket;
	private BufferedReader br = null;
	private PrintStream ps = null;
	private ChatServer window;

	public ChatSocket(Socket socket, ChatServer window) {
		this.setSocket(socket);
		this.window = window;
	}

	@Override
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			ps = new PrintStream(socket.getOutputStream());
			String line = null;
			while ((line = readLine()) != null) {
				window.getJTextArea1().append(line + "\r\n");
				if (line.contains("@")) {
					ChatManager.getChatManager().out(this, line, "@");
				} else {
					ChatManager.getChatManager().out(this, line);
				}
			}
			br.close();
			ps.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	private String readLine() {
		try {
			return br.readLine();
		} catch (IOException e) {
			window.listModel.removeElement(ChatManager.getChatManager().getKey(this));
			window.getJTextArea0().append(ChatManager.getChatManager().getKey(this) + "已断开!\r\n");
			ChatManager.getChatManager().remove(ChatManager.getChatManager().getKey(this));
			try {
				this.socket.close();
				this.stop();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		return null;
	}

	public void out(String msg) {
		if (br != null) {
			ps.println(msg);
		} else {
			JOptionPane.showMessageDialog(null, "连接已断开!");
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
