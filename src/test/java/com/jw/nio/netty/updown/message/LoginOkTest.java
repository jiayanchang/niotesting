package com.jw.nio.netty.updown.message;

import junit.framework.Assert;

import org.jboss.netty.buffer.ChannelBuffer;
import org.junit.Test;

import com.jw.nio.netty.login.message.LoginOk;

public class LoginOkTest {

	@Test
	public void test() {
		LoginOk loginOk = new LoginOk();
		loginOk.setUsername("james");
		
		ChannelBuffer buffer = loginOk.encode();
		System.out.println(buffer.toString());
		
		LoginOk tgt = new LoginOk();
		tgt.decode(buffer);
		System.out.println(tgt.getUsername());
		
		Assert.assertEquals(loginOk.getUsername(), tgt.getUsername());
		
//		fail("Not yet implemented");
	}

}
