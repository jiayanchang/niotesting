package com.jw.nio.netty.time;

public class TimeTest {

	public static void main(String[] args) {
		for(int i = 0; i < 2; i++){
			System.out.println(i + " : " + TimeClient.getInstance().req());
		}
	}
}
