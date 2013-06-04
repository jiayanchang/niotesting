package com.jw.nio.netty.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class Decoder extends FrameDecoder {

	static final Map<Integer, Class<?>> types = new HashMap<Integer, Class<?>>();

	static {
		for (MessageType type : MessageType.values()) {
			types.put(type.type, type.clz);
		}
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		print("decode");
		int type = buffer.readByte();
		Class<?> clz = types.get(type);
		Object message = clz.newInstance();
		clz.getMethod("decode", ChannelBuffer.class).invoke(message, buffer);
		return message;
	}
	
	private void print(String msg) {
		System.out.println(System.currentTimeMillis() + " " + this.getClass().getSimpleName() + " : " + msg);
	}
}
