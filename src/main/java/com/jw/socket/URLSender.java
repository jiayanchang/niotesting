package com.jw.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownHostException;

public class URLSender {
	/** * @param args */
	public static void main(String[] args) throws IOException {
		try {
//			InetSocketAddress srv = new InetSocketAddress("218.240.46.252",
//					8808);
			InetSocketAddress srv = new InetSocketAddress("192.168.6.39",
					350);
			Socket socket = new Socket(new Proxy(Proxy.Type.SOCKS, srv));
//			Socket socket = new Socket("192.168.6.39", 350);
//			Authenticator.setDefault(new Authenticator() {
//				@Override
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("ppprroxy", "000449xy"
//							.toCharArray());
//				}
//			});
//			 Socket socket = new Socket("www.nwu.edu.cn", 80);
			System.out.println("connecting...");
			socket.setSoTimeout(5000);
			socket.connect(new InetSocketAddress("192.168.7.6", 8000), 5000);
			System.out.println("socket.isClosed() :　" + socket.isClosed());
			sendHttp(socket);
			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: Victest.");
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: Victest.");
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void sendHttp(Socket socket) throws IOException {
		boolean autoflush = true;
		PrintWriter out = new PrintWriter(socket.getOutputStream(),
				autoflush);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		// send an HTTP request to the web server
		out.println("GET /bcs/menu.html HTTP/1.1");
		out.println("Host: http://192.168.7.6:8000");
		out.println("Accept: text/html");
		out.println("Connection: Close");
		out.println();
		// read the response
		boolean loop = true;
		StringBuffer sb = new StringBuffer(8096);
		while (loop) {
			if (in.ready()) {
				int i = 0;
				while (i != -1) {
					i = in.read();
					sb.append((char) i);
				}
				loop = false;
			}
			// Thread.currentThread().sleep(50);
		}
		// display the response to the out console
		System.out.println("---------------------");
		System.out.println(sb.toString());
		System.out.println("---------------------");
	}
}