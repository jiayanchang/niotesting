package com.jw.nio.netty.helloworld;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class HelloServer {

	public static void main(String[] args) {
		ServerBootstrap boot = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		//ServerBootstrap创建别接管一个channelFactory，factory创建通道，channel监管配置信息，通道状态等信息
		boot.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new HelloChannelHandler());
			}
		});
		
		boot.bind(new InetSocketAddress(8000));
		System.out.println("hello server is startup.");
	}

	private static class HelloChannelHandler extends SimpleChannelHandler {
		@Override
		public void channelConnected(ChannelHandlerContext ctx,
				ChannelStateEvent e) throws Exception {
			System.out.println("hello world! i'm server.");
			super.channelConnected(ctx, e);
		}
	}
	
}
