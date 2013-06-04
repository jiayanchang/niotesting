package com.jw.nio.netty.login.server;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;

import com.jw.nio.netty.login.message.LoginFail;
import com.jw.nio.netty.login.message.LoginOk;

public class LoginLogHandler extends SimpleChannelDownstreamHandler {

	
	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		if(e.getMessage() instanceof LoginOk){
			print("登陆成功");
		} else if(e.getMessage() instanceof LoginFail){
			print("登陆失败");
		} else {
			print("其他 " + e.getMessage().getClass().getSimpleName());
		}
		super.writeRequested(ctx, e);
	}
	
	private void print(String msg) {
		System.out.println(System.currentTimeMillis() + " " + this.getClass().getSimpleName() + " : " + msg);
	}
}
