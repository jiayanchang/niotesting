package com.jw.nio.netty.login.message;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.jw.nio.netty.login.MessageType;

public class LoginFail extends AbstractMessage {

	public LoginFail() {
		super(MessageType.loginFail.type);
	}
	
	private int code;
	private String error;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public void decode(ChannelBuffer buffer) {
		setCode(buffer.readInt());
		setError(buffer.readBytes(buffer.readableBytes()).toString(Charset.defaultCharset()));
	}

	@Override
	public ChannelBuffer encode() {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer(20);
		buffer.writeByte(type);
		buffer.writeByte(getCode());
		buffer.writeBytes(getError().getBytes());
		return buffer;
	}

}
