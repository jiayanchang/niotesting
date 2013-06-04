package com.jw.nio.netty.login.server;

import java.util.Date;
import java.util.HashMap;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.jw.nio.netty.login.message.LoginFail;
import com.jw.nio.netty.login.message.LoginOk;
import com.jw.nio.netty.login.message.LoginRequest;

public class LoginHandler extends SimpleChannelUpstreamHandler {

	public static HashMap<String, String> logins = new HashMap<String, String>();

	static {
		logins.put("james", "111");
		logins.put("kobe", "111");
		logins.put("wade", "111");
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		print("开始登陆");
		if (e.getMessage() instanceof LoginRequest) {
			LoginRequest loginRequest = (LoginRequest) e.getMessage();
			if (logins.containsKey(loginRequest.getUser().getUsername())
					&& logins.get(loginRequest.getUser().getUsername())
							.equalsIgnoreCase(
									loginRequest.getUser().getPassword())) {
				print("登陆成功");
				LoginOk loginOk = new LoginOk();
				loginOk.setUsername(loginRequest.getUser().getUsername());
				e.getChannel().write(loginOk.encode());
			} else {
				print("登陆失败");
				LoginFail fail = new LoginFail();
				fail.setCode(0);
				fail.setError("登陆错误");
				e.getChannel().write(fail);
			}
		} else {
			print("非登陆");
		}
		super.messageReceived(ctx, e);
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		print("channelOpen.");
		super.channelOpen(ctx, e);
	}

	private void print(String msg) {
		System.out.println(System.currentTimeMillis() + " " + this.getClass().getSimpleName() + " : " + msg);
	}
}