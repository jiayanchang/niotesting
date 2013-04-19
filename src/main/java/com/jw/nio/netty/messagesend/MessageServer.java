package com.jw.nio.netty.messagesend;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class MessageServer {
	
	public static void main(String[] args) {
		//��server��ע��һ��ͨ���Ĺ��������ڴ���ͨ��
		ServerBootstrap server = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		//��server��ע��һ���ܵ��Ĺ�����ָ����Щ�ܵ�ִ����Щhandler
		server.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("test.recevied", new MessageChannelHandler());
				pipeline.addLast("test.fivebyte", new FiveByteReadChannelHander());
				//��ܵ���װ��channelHandler������ָ��ܵ��Ĵ����¼�
				return pipeline;
			}
		});
		//server�󶨼����˿�
		server.bind(new InetSocketAddress(8000));
		System.out.println("server is startup.");
	}
	
	private static class MessageChannelHandler extends SimpleChannelHandler {
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			ChannelBuffer channelBuffer = (ChannelBuffer)e.getMessage();
			System.out.println(MessageChannelHandler.class.getName() + " received : " + channelBuffer.toString(Charset.defaultCharset()));
			super.messageReceived(ctx, e);
		}
	}
	
	public static class FiveByteReadChannelHander extends SimpleChannelHandler {
		
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			ChannelBuffer buffer = (ChannelBuffer)e.getMessage();
			while (buffer.readableBytes() > 5){
				ChannelBuffer temp = buffer.readBytes(5);
				System.out.println(FiveByteReadChannelHander.class.getName() + " received : " + temp.toString(Charset.defaultCharset()));
			}
			System.out.println(FiveByteReadChannelHander.class.getName() + " received : " + buffer.toString(Charset.defaultCharset()));
		}
	}
}
