package com.gpg.client;

import javax.swing.SwingUtilities;

import com.gpg.ui.MainScreen;

public class ClientStartRun {
	public static void main(String[] args) throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainScreen mainScreen = new MainScreen();
				mainScreen.setVisible(true);
			}
		});
	}
}
