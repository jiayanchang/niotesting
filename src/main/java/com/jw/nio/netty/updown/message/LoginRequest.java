package com.jw.nio.netty.updown.message;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.jw.nio.netty.updown.MessageType;

public class LoginRequest extends AbstractMessage {

	public LoginRequest(){
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
		user = readXml(User.class, buffer.readBytes(buffer.readableBytes()).toString(Charset.defaultCharset()));
	}

	@Override
	public ChannelBuffer encode() {
		
		System.out.println("login request encode");
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		try {
			buffer.writeByte(type);
			buffer.writeBytes(toXml(getUser()).getBytes(Charset.defaultCharset()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}

}
