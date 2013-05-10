package com.jw.nio.netty.messagesend;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
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

public class TransactionClient {

	public static void main(String[] args) {
		
		ClientBootstrap client = new ClientBootstrap(new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		
		client.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new MessageHandler());
			}
			
		});
		
		ChannelFuture future = client.connect(new InetSocketAddress("127.0.0.1", 8000));
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		client.shutdown();
	}
	
	private static class MessageHandler extends SimpleChannelHandler {
		@Override
		public void channelConnected(ChannelHandlerContext ctx,
				ChannelStateEvent e) throws Exception {
			String msg = "i'm a client.";
			ChannelBuffer buffer = ChannelBuffers.buffer(msg.length());
			buffer.writeBytes(msg.getBytes(Charset.defaultCharset()));
			e.getChannel().write(buffer);
			System.out.println("send msg : " + msg);
//			e.getChannel().close();
			Thread.sleep(1000);
			buffer.clear();
			buffer.writeBytes(msg.getBytes(Charset.defaultCharset()));
			e.getChannel().write(buffer);
			System.out.println("send msg again : " + msg);
		}
	}
}
