package com.jw.nio.netty.updown.client;

import java.util.Date;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.WriteCompletionEvent;

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
		print("连接完成");
		LoginRequest req = new LoginRequest();
		req.setUser(user);
		print("请求服务器端登陆");
		e.getChannel().write(req);
		print("请求服务器端登陆完成");
//		super.channelConnected(ctx, e);
	}
	
	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e)
			throws Exception {
		print("writeComplete");
		super.writeComplete(ctx, e);
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		print("messageReceived");
		super.messageReceived(ctx, e);
	}
	
	private void print(String msg) {
		System.out.println(new Date() + " " + this.getClass().getName() + " : " + msg);
	}
	
	
}
