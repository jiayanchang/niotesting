package com.jw.nio.netty.login;

import com.jw.nio.netty.login.message.LoginFail;
import com.jw.nio.netty.login.message.LoginOk;
import com.jw.nio.netty.login.message.LoginRequest;

public enum MessageType {

	loginRequest(0x1a, LoginRequest.class),
	loginOk(0x1b, LoginOk.class),
	loginFail(0x1c, LoginFail.class), ;

	private MessageType(int type, Class<?> clz) {
		this.type = type;
		this.clz = clz;
	}

	public final int type;

	public final Class<?> clz;


}
