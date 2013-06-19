package com.jw.nio.netty.time;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

public class TimeContext {

	public TimeContext(Channel channel) {
		this.channel = channel;
		this.channel.setAttachment(this);
		lock = new ReentrantLock();
		condition = lock.newCondition();
	}
	
	final Channel channel;
	final ReentrantLock lock;
	final Condition condition;
	
	private Object msg;
	
	public String request(String msg){
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer(msg.length());
		buffer.writeBytes(msg.getBytes());
		channel.write(buffer);
		return await(2).toString();
	}
	
	public Object await(int timeOut){
		lock.lock();
		try {
			if(msg == null){
				if(!condition.await(timeOut, TimeUnit.SECONDS)){
					throw new InterruptedException();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return msg;
	}
	
	public void setReply(Object msg){
		lock.lock();
		try {
			this.msg = msg;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
}
