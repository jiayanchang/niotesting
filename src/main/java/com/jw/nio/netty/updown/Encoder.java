package com.jw.nio.netty.updown;

import java.util.Date;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.DownstreamMessageEvent;
import org.jboss.netty.channel.MessageEvent;

import com.jw.nio.netty.updown.message.AbstractMessage;

public class Encoder implements ChannelDownstreamHandler {

	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		print("encode event : " + e.getClass().getName());
		if(e instanceof MessageEvent){
			if(((MessageEvent) e).getMessage() instanceof AbstractMessage) {
				ChannelBuffer buffer = ((AbstractMessage)((MessageEvent) e).getMessage()).encode();
				e.getChannel().write(buffer);
				print("encode successful");
				ctx.sendDownstream(new DownstreamMessageEvent(ctx.getChannel(), e.getFuture(), buffer, ((MessageEvent)e).getRemoteAddress()));
			} else {
				print("encode failure " + ((MessageEvent) e).getMessage().getClass().getName());
				ctx.sendDownstream(e);
			}
		} else {
			ctx.sendDownstream(e);
		}
		
	}

	private void print(String msg) {
		System.out.println(new Date() + " " + this.getClass().getName() + " : " + msg);
	}
}
