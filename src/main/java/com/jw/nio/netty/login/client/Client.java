package com.jw.nio.netty.login.client;

import java.net.InetSocketAddress;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.jw.nio.netty.login.Decoder;
import com.jw.nio.netty.login.Encoder;

public class Client {

	public static ClientBootstrap client;
	
//	private static 
	
	public static void main(String[] args) {
		init();
		InetSocketAddress svr = new InetSocketAddress("localhost", 8000);
		long time = System.currentTimeMillis();
		for(int i = 0; i < 5; i++){
			ChannelFuture future = client.connect(svr);
			future.getChannel().getCloseFuture().awaitUninterruptibly();
			System.out.println("attachment : " + future.getChannel().getAttachment());
			System.out.println(System.currentTimeMillis() - time);
		}
		
//		try {
//			Channel channel = future.awaitUninterruptibly().getChannel();
//			future.
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
	
	private static void init(){
		client = new ClientBootstrap(new NioClientSocketChannelFactory());
		client.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline =  Channels.pipeline();
				pipeline.addLast(Encoder.class.getName(), new Encoder());
				pipeline.addLast(Decoder.class.getName(), new Decoder());
				pipeline.addLast(LoginRequestHandler.class.getName(), new LoginRequestHandler("james", "111"));
				pipeline.addLast(LoginSuccessHandler.class.getName(), new LoginSuccessHandler());
				return pipeline;
			}
		});
	}
	
//	public static String req(){
//		
//	}
	
}
