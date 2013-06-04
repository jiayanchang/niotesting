package com.jw.nio.netty.login.message;

import java.nio.charset.Charset;
import java.util.Date;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.jw.nio.netty.login.MessageType;

public class LoginRequest extends AbstractMessage {

	public LoginRequest() {
		super(MessageType.loginRequest.type);
	}

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void decode(ChannelBuffer buffer) {
		user = readXml(User.class, buffer.readBytes(buffer.readableBytes())
				.toString(Charset.defaultCharset()));
	}

	@Override
	public ChannelBuffer encode() {

		print("login request encode");
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		try {
			buffer.writeByte(type);
			buffer.writeBytes(toXml(getUser()).getBytes(
					Charset.defaultCharset()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}

	private void print(String msg) {
		System.out.println(System.currentTimeMillis() + " " + this.getClass().getName() + " : "
				+ msg);
	}
}
