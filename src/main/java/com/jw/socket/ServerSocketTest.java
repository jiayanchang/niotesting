package com.jw.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

	public static void main(String[] args) {
		Socket socket = null;
		try {
			ServerSocket server = new ServerSocket(8000);
			while(true){
				socket = server.accept();
				System.out.println(socket.getInetAddress().getHostAddress());
				socket.getOutputStream().write("hello".getBytes());
				socket.getOutputStream().close();
				Thread.sleep(1000);
				socket.close();
				System.out.println("closed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(socket != null) {
					socket.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
