package com.jw.nio.netty.updown.client;

import java.util.Date;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.jw.nio.netty.updown.message.LoginFail;
import com.jw.nio.netty.updown.message.LoginOk;

public class LoginSuccessHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		print("messageReceived");
		if(e.getMessage() instanceof LoginOk){
			print("loginOk");
		} else if (e.getMessage() instanceof LoginFail){
			print("LoginFail");
		} else {
			print("other");
		}
//		super.messageReceived(ctx, e);
	}
	
	private void print(String msg){
		System.out.println(new Date() + " " + this.getClass().getName() + " : " + msg);
	}

}