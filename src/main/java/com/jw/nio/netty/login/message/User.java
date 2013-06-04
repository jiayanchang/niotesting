package com.jw.nio.netty.login.message;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "USER")
public class User {
	
	@Attribute(name = "USERNAME", required = false)
	private String username;
	
	@Attribute(name = "PASSWORD", required = false)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
