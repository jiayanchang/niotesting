package com.jw.nio.netty.updown;

import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import com.jw.nio.netty.updown.message.AbstractMessage;

public class Encoder implements ChannelDownstreamHandler {

	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		if(e instanceof MessageEvent){
			if(((MessageEvent) e).getMessage() instanceof AbstractMessage) {
				e.getChannel().write(((AbstractMessage)((MessageEvent) e).getMessage()).encode());
			} 
		} else {
			ctx.sendDownstream(e);
		}
		
	}

}
