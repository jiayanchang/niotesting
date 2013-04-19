package com.jw.nio.netty.messagesend;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class MessageClient {

	static ClientBootstrap client = new ClientBootstrap(new NioClientSocketChannelFactory(
			Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
	
	public static void main(String[] args) {
		
		client.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("handler", new MessageChannelHandler());
				return pipeline;
			}
		});
		
		ChannelFuture future = client.connect(new InetSocketAddress("127.0.0.1", 8000));
		System.out.println("client is requested.");
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		client.releaseExternalResources();
	}
	
	private static class MessageChannelHandler extends SimpleChannelHandler {
		@Override
		public void channelConnected(ChannelHandlerContext ctx,
				ChannelStateEvent e) throws Exception {
			String msg = "i'm client. i send this message";
			ChannelBuffer buffer = ChannelBuffers.buffer(msg.length()); 
			buffer.writeBytes(msg.getBytes());
			e.getChannel().write(buffer);
			ctx.getChannel().close();
		}
		
		@Override
		public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
				throws Exception {
			System.out.println("channelClosed");
		}
	}
}
