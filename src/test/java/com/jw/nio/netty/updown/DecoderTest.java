package com.jw.nio.netty.updown;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.jw.nio.netty.updown.message.LoginOk;
import com.jw.nio.netty.updown.message.LoginRequest;

public class DecoderTest {

	@Test
	public void test() {
		Decoder decoder = new Decoder();
		LoginOk request = new LoginOk();
		request.setUsername("james");
		try {
			LoginOk rst = (LoginOk) decoder.decode(null, null, request.encode());
			System.out.println(rst.getUsername());
			Assert.assertEquals(request.getUsername(), rst.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
