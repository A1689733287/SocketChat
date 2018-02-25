package com.gpg.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
		getMap().put(name, sockerThtead);
		System.out.println(map.size());
	}
	
	/**
	 * 移除线程
	 * @param cs
	 */
	public void remove (String key) {
		getMap().remove(key);
		
	}
	

	/**
	 * 发送消息
	 * @param cs 发送消息的线程
	 * @param msg 发送的消息
	 */
	public void out(ChatSocket cs, String msg) {
		Set<Entry<String, Thread>> entrySet = getMap().entrySet();
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
	 * @param regix 进行验证@
	 */
	public void out(ChatSocket cs, String msg, String regix) {
		boolean flag = false;
		Set<String> keySet = getMap().keySet();
		for (String string : keySet) {
			if (msg.contains(regix + string)) {
				flag = true;
				cs = (ChatSocket) getMap().get(string);
				String[] split = msg.split(regix);
				msg = split[0]
						+ msg.substring(msg.indexOf(regix) + (regix + string).length(), msg.length());
				System.out.println(msg.indexOf(regix) + (regix + string).length() + 1);
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
		Set<String> keySet = getMap().keySet();
		for (String string : keySet) {
			if (getMap().get(string).equals(cs)) {
				return string;
			}
		}
		return null;
	}

	public Map<String, Thread> getMap() {
		return map;
	}

	public void setMap(Map<String, Thread> map) {
		this.map = map;
	}

}
