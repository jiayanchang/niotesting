package com.jw.nio.netty.time;

import java.net.InetSocketAddress;

public class TimeTest {

	public static void main(String[] args) {
		InetSocketAddress server = new InetSocketAddress("localhost", 8000);
		for(int x = 0; x < 100; x++){
			TimeClient client = new TimeClient(server);
			long runtime = System.currentTimeMillis();
			client.connect();
			for(int i = 0; i < 50; i++){
				System.out.println("第" + x +"个->第" + i + "次 : " + client.request());
				client.request();
			}
			client.close();
			System.out.println("第" + x +"个运行时间：" + (System.currentTimeMillis() - runtime));
		}
		System.exit(0);
	}
}
