package com.jw.nio.netty;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Test {

	public static void main(String[] args) {
		try {
			throw new NullPointerException();
		} catch (Exception e){
			System.out.println(getStackTraceMessage(e));
			System.out.println(getStackTraceMessage2(e));
		}
	}

	private static String getStackTraceMessage(Exception e) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.getClass().getName() + " : " + e.getMessage());
		if(e.getStackTrace() != null){
			for(StackTraceElement eele : e.getStackTrace()){
				sb.append("\r\tat " + eele.toString());
			}
		}
		return sb.toString();
	}
	private static String getStackTraceMessage2(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.close();
		return sw.toString();
	}
}
