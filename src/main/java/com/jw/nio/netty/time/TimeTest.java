package com.jw.nio.netty.time;

import java.net.InetSocketAddress;

public class TimeTest {

	public static void main(String[] args) {
		InetSocketAddress server = new InetSocketAddress("localhost", 8000);
		MultiTimeClient.init(server);
		for (int i = 0; i < 50; i++) {
			MultiTimeClient client = new MultiTimeClient(i + "");
			new Thread(client).start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
