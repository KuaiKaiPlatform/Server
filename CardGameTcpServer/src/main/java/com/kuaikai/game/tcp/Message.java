package com.kuaikai.game.tcp;

public class Message {
	public final int msgid;

	public final byte[] bytes;

	public Message(int msgid, byte[] bytes) {
		this.msgid = msgid;
		this.bytes = bytes;
	}

	public Message(int msgid) {
		this(msgid, null);
	}
}
