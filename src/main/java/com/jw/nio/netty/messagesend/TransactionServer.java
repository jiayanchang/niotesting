package com.jw.nio.netty.messagesend;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class TransactionServer {

	/**
	 * 打印信息是 :
	 * Transcation 1
	 * Processor 1
	 * Processor 2
	 * Transcation 2
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ServerBootstrap server = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		
		server.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				
				pipeline.addLast(TranscationHandler.class.getName(), new TranscationHandler());
				pipeline.addLast(ProcessorHandler.class.getName(), new ProcessorHandler());
				
				return pipeline;
			}
		});
		
		server.bind(new InetSocketAddress(8000));
		System.out.println("server is startup.");
	}
	
	private static class TranscationHandler extends SimpleChannelHandler {
		
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			System.out.println("Transcation 1");
			super.messageReceived(ctx, e);
			System.out.println("Transcation 2");
			ctx.getPipeline().remove(this.getClass().getName());
			System.out.println(TranscationHandler.class.getName() + " : " + this.getClass().getName());
		}
	}
	
	private static class ProcessorHandler extends SimpleChannelHandler {
		
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			System.out.println("Processor 1");
			super.messageReceived(ctx, e);
			System.out.println("Processor 2");
		}
	}
}
