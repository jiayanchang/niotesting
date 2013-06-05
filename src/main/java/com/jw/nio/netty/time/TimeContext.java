package com.jw.nio.netty.time;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TimeContext {

	public TimeContext() {
		lock = new ReentrantLock();
		condition = lock.newCondition();
	}
	
	final ReentrantLock lock;
	final Condition condition;
	
	private Object msg;
	
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
	
	public static void main(String[] args) {
		TimeContext context = new TimeContext();
		context.await(20);
	}
}
