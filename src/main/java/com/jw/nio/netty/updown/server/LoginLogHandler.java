package com.jw.nio.netty.updown.server;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.jw.nio.netty.updown.message.LoginFail;
import com.jw.nio.netty.updown.message.LoginOk;

public class LoginLogHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		if(e.getMessage() instanceof LoginOk){
			print("登陆成功");
		} else if(e.getMessage() instanceof LoginFail){
			print("登陆失败");
		}
//		super.messageReceived(ctx, e);
	}
	
	private void print(String msg) {
		System.out.println(this.getClass().getName() + " : " + msg);
	}
}