package com.jw.nio.netty.login.message;

import java.io.StringWriter;
import java.io.Writer;

import org.jboss.netty.buffer.ChannelBuffer;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public abstract class AbstractMessage {
	
	int type;
	
    static private final Serializer persister = new Persister();

    public AbstractMessage(int type){
    	this.type = type;
    }
    
    public static String toXml(Object message){
    	Writer writer = new StringWriter();
    	try {
			persister.write(message, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return writer.toString();
    }
    
	public static <T> T readXml(Class<?> clz , String xml){
		try {
			return (T) persister.read(clz, xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public abstract void decode(ChannelBuffer buffer);
	
	public abstract ChannelBuffer encode();
}
