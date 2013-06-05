package com.jw.nio.netty.time;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class TimeServer {

	static ServerBootstrap server;

	static final int port = 8000;

	public static final InetSocketAddress address = new InetSocketAddress(port);

	static NioServerSocketChannelFactory channelFactory = new NioServerSocketChannelFactory(
			Executors.newCachedThreadPool(), 2,
			Executors.newCachedThreadPool(), 20);

	static ChannelPipelineFactory channelPipelineFactory = new ChannelPipelineFactory() {
		@Override
		public ChannelPipeline getPipeline() throws Exception {
			ChannelPipeline pipeline = Channels.pipeline();
			pipeline.addLast(TimeHandler.class.getName(), new TimeHandler());
			return pipeline;
		}
	};

	static {
		server = new ServerBootstrap(channelFactory);
		server.setOption("backlog", 1000);
		server.setOption("reuseAddress", true);
		server.setOption("child.tcpNoDelay", true);
		server.setPipelineFactory(channelPipelineFactory);
	}
	
	public static void main(String[] args) {
		server.bind(address);
		System.out.println("server is startup");
	}
	
	private static class TimeHandler extends SimpleChannelHandler {
		
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			String req = ((ChannelBuffer)e.getMessage()).toString(Charset.defaultCharset());
			if(req.equals("1")) {
				String msg = "" + Math.random();
				ChannelBuffer buffer = ChannelBuffers.dynamicBuffer(msg.length());
				buffer.writeBytes(msg.getBytes());
				e.getChannel().write(buffer);
				System.out.println("write over");
			}
			super.messageReceived(ctx, e);
		}
	}

}
