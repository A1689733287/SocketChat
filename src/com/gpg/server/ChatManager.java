package com.gpg.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;

import java.util.Set;

public class ChatManager {
	private final static ChatManager instance = new ChatManager();
	// 装载用户容器
	private Map<String, Thread> map = new HashMap<>();

	private ChatManager() {
	}

	public static ChatManager getChatManager() {
		return instance;
	}
	/**
	 * 发消息线程添加到集合当中
	 * @param name 线程的名字
	 * @param sockerThtead 消息线程
	 */
	public void add(String name, Thread sockerThtead) {
		map.put(name, sockerThtead);
	}
	
	/**
	 * 移除线程
	 * @param cs
	 */
	public void remove (ChatSocket cs) {
		map.remove(cs);
	}

	/**
	 * 发送消息
	 * @param cs 发送消息的线程
	 * @param msg 发送的消息
	 */
	public void out(ChatSocket cs, String msg) {
		Set<Entry<String, Thread>> entrySet = map.entrySet();
		for (Entry<String, Thread> entry : entrySet) {
			ChatSocket csTemp = (ChatSocket) entry.getValue();
			System.out.println(csTemp);
			if (!cs.equals(csTemp)) {
				csTemp.out(msg);
			}

		}
	}

	/**
	 * 发送消息
	 * @param cs 发送消息线程
	 * @param msg 传递的消息
	 * @param regix 进行验证
	 */
	public void out(ChatSocket cs, String msg, String regix) {
		boolean flag = false;
		Set<String> keySet = map.keySet();
		for (String string : keySet) {
			if (msg.contains(regix + string)) {
				flag = true;
				cs = (ChatSocket) map.get(string);
				String[] split = msg.split(regix);
				msg = split[0]
						+ msg.substring(msg.indexOf(regix) + (msg.indexOf(regix) + string).length() + 1, msg.length());
			}
		}
		if (flag) {
			cs.out(msg);
		} else {
			out(cs, msg);
		}
	}
	/**
	 * 返回通过value获取map的key值
	 * @param cs map中的Vlaue值
	 * @return 返回key值
	 */
	public String getKey(ChatSocket cs) {
		Set<String> keySet = map.keySet();
		for (String string : keySet) {
			if (map.get(string).equals(cs)) {
				return string;
			}
		}
		return null;
	}

}
