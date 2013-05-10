package com.jw.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ip {
	
	
	public static void main(String[] args) {
		try {
			System.out.println(InetAddress.getByName(null));
			System.out.println(InetAddress.getByName("WINMICR-GAV4JDV"));
			System.out.println(InetAddress.getAllByName(null));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
