package com.jw.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class TestCases {

	@Test
	public void testHttpReq(){
		try {
			Socket s = new Socket("www.pconline.com.cn",80);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(),"GBK"));
			OutputStream out = s.getOutputStream();
			StringBuffer sb = new StringBuffer("GET /index.html HTTP/1.1\r\n");
			sb.append("User-Agent: Java/1.6.0_20\r\n");
			sb.append("Host: www.pconline.com.cn:80\r\n");
			sb.append("Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\n");
			sb.append("Connection: Close\r\n");
			sb.append("\r\n");
			out.write(sb.toString().getBytes());
			String tmp = "";
			while((tmp = br.readLine())!=null){
				System.out.println(tmp);
			}
			out.close();
			br.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testSocketReq() {
		try {
			InetSocketAddress targetSrv = new InetSocketAddress("localhost",
					8000);
			Socket socket = new Socket("localhost", 8000);
			socket.connect(targetSrv);
			InputStream in = socket.getInputStream();
			BufferedReader bis = new BufferedReader(new InputStreamReader(in));
			System.out.println(bis.readLine());
			System.out.println(socket.isConnected());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(1111);
	}

}
