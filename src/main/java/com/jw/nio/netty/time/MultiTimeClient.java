package com.jw.nio.netty.time;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class MultiTimeClient implements Runnable {

	public MultiTimeClient(String flag){
		this.flag = flag;
	}
	
	String flag;
	
	static InetSocketAddress server;
	static Channel channel;
	static ClientBootstrap client;
	
	static {
		client = new ClientBootstrap(new NioClientSocketChannelFactory());
		client.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast(ReceivableHandler.class.getName(), new ReceivableHandler());
				return pipeline;
			}
		});
	}
	
	public static void init(InetSocketAddress server){
		MultiTimeClient.server = server;
		connect();
	}
	
	private static void connect(){
		ChannelFuture future = client.connect(server);
		future.awaitUninterruptibly();
		channel = future.getChannel();
	}
	
	public static void close(){
		channel.close();
	}
	
	@Override
	public void run() {
		TimeContext context = new TimeContext(channel);
		System.out.println(this.flag + " : " + context.request(flag));
	}

	
	private static class ReceivableHandler extends SimpleChannelHandler {
		
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
			String msg = buffer.toString(Charset.defaultCharset());
			TimeContext timeContext = (TimeContext) ctx.getChannel().getAttachment();
			System.out.println("ReceivableHandler.setAttachment(" + msg + ")");
			timeContext.setReply(msg);
			super.messageReceived(ctx, e);
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
				throws Exception {
			super.exceptionCaught(ctx, e);
		}
	}

}
