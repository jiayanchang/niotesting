package com.jw.nio.netty.login.message;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.jw.nio.netty.login.MessageType;

public class LoginOk extends AbstractMessage {
	
	public LoginOk() {
		super(MessageType.loginOk.type);
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void decode(ChannelBuffer buffer) {
		username = buffer.readBytes(buffer.readableBytes()).toString(
				Charset.defaultCharset());
	}

	@Override
	public ChannelBuffer encode() {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeByte(type);
		buffer.writeBytes(getUsername().getBytes());
		return buffer;
	}

}
