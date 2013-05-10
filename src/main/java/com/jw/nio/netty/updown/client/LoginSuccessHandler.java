package com.jw.nio.netty.updown.client;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.jw.nio.netty.updown.message.LoginFail;
import com.jw.nio.netty.updown.message.LoginOk;

public class LoginSuccessHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		if(e.getMessage() instanceof LoginOk){
			print("loginOk");
		} else if (e.getMessage() instanceof LoginFail){
			print("LoginFail");
		} else {
			print("other");
		}
	}
	
	private void print(String msg){
		System.out.println(this.getClass().getName() + " : " + msg);
	}

}