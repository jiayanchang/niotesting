package com.jw.nio.netty.updown.client;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.jw.nio.netty.updown.message.LoginRequest;
import com.jw.nio.netty.updown.message.User;

public class LoginRequestHandler extends SimpleChannelUpstreamHandler {

	private User user;
	
	public LoginRequestHandler(User user){
		this.user = user;
	}
	
	public LoginRequestHandler(String username, String password){
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		LoginRequest req = new LoginRequest();
		req.setUser(user);
		print("请求服务器端登陆");
		e.getChannel().write(req);
		print("请求服务器端登陆完成");
		super.channelConnected(ctx, e);
	}
	
	private void print(String msg) {
		System.out.println(this.getClass().getName() + " : " + msg);
	}
}
