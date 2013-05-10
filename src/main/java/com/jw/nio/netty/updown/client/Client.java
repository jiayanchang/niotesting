package com.jw.nio.netty.updown.client;

import java.net.InetSocketAddress;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.jw.nio.netty.updown.Decoder;
import com.jw.nio.netty.updown.Encoder;

public class Client {

	public static void main(String[] args) {
		InetSocketAddress svr = new InetSocketAddress("localhost", 8000);
		ClientBootstrap client = new ClientBootstrap(new NioClientSocketChannelFactory());
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
		client.connect(svr);
	}
}
