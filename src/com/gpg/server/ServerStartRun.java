package com.gpg.server;


import java.io.IOException;

import com.gpg.ui.ChatServer;

public class ServerStartRun {
	
	public static void main(String[] args) throws IOException {
		ChatServer chatServer = new ChatServer();
		chatServer.setVisible(true);
	}
}
