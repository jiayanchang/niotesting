package com.jw.nio.netty.updown.message;

import junit.framework.Assert;

import org.jboss.netty.buffer.ChannelBuffer;
import org.junit.Test;

import com.jw.nio.netty.login.message.LoginRequest;
import com.jw.nio.netty.login.message.User;

public class LoginRequestTest {

	@Test
	public void test() {
		User user = new User();
		user.setPassword("111");
		user.setUsername("james");
		LoginRequest sour = new LoginRequest();
		sour.setUser(user);
		
		LoginRequest tgt = new LoginRequest();
		ChannelBuffer buf = sour.encode();
		buf.readByte();//将type读出
		tgt.decode(buf);
		
		Assert.assertEquals(tgt.getUser().getUsername(), sour.getUser().getUsername());
	}

}
