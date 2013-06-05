package com.jw.nio.netty.time;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class TimeClient {

	static InetSocketAddress server = new InetSocketAddress("localhost", 8000);
	static ClientBootstrap client;
	static Channel channel;
	
	private String msg;
	
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
		ChannelFuture future = client.connect(server);
		future.awaitUninterruptibly();
		channel = future.getChannel();
	}
	
	public static TimeClient getInstance() {
		TimeClient client = new TimeClient();
		return client;
	}
	
	public String req(){
		String msg = "1";
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer(msg.length());
		buffer.writeBytes(msg.getBytes());
		ChannelFuture future = channel.write(buffer);
		future.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				// TODO Auto-generated method stub
				this.notify();
			}
		});
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("req over");
		return (String) future.getChannel().getAttachment();
	}
	
	private static class ReceivableHandler extends SimpleChannelHandler {
		
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
			String msg = buffer.toString(Charset.defaultCharset());
			System.out.println("ReceivableHandler.setAttachment(" + msg + ")");
			ctx.getChannel().setAttachment(msg);
			super.messageReceived(ctx, e);
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
				throws Exception {
			super.exceptionCaught(ctx, e);
		}
	}
}
