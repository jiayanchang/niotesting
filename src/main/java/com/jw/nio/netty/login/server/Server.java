package com.jw.nio.netty.login.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.jw.nio.netty.login.Decoder;
import com.jw.nio.netty.login.Encoder;

public class Server {

	public static void main(String[] args) {
		InetSocketAddress localAddress = new InetSocketAddress(8000);
		ServerBootstrap server = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(), 1,
						Executors.newCachedThreadPool(), 1));
		server.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast(Encoder.class.toString(), new Encoder());
				pipeline.addLast(Decoder.class.toString(), new Decoder());
				pipeline.addLast(LoginHandler.class.toString(),
						new LoginHandler());
				pipeline.addLast(LoginLogHandler.class.toString(),
						new LoginLogHandler());
				return pipeline;
			}
		});
		System.out.println("server started.");
		server.bind(localAddress);
	}

	public static class Context {

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
