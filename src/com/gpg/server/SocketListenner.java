package com.gpg.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.gpg.ui.ChatServer;

public class SocketListenner extends Thread {
	private int port;
	private ChatServer window;
	public boolean isOpen = false;

	public SocketListenner(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		ServerSocket socketServer = null;
		Socket socket = null;
		try {
			socketServer = new ServerSocket(port);
			isOpen = true;
			while (true) {
				socket = socketServer.accept();
				ChatSocket cs = new ChatSocket(socket,window);
				cs.start();
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				String line = br.readLine();
				String[] split = line.split("#");
				window.listModel.addElement(split[0]);
				window.getJTextArea0().append(line + "已连接!\r\n");
				ChatManager.getChatManager().add(split[0], cs);
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public ChatServer getChatServer() {
		return window;
	}

	public void setChatServer(ChatServer window) {
		this.window = window;
	}
}
