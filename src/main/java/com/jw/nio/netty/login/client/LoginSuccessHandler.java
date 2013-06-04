package com.jw.nio.netty.login.client;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.jw.nio.netty.login.message.LoginFail;
import com.jw.nio.netty.login.message.LoginOk;

public class LoginSuccessHandler extends SimpleChannelUpstreamHandler {

	private void log(ChannelHandlerContext ctx, Object message, String method) {
		print(method + " isConnected : " + ctx.getChannel().isConnected());
		if(message != null){
			ctx.getChannel().setAttachment(message);
			if(message instanceof LoginOk){
				print("loginOk");
			} else if (message instanceof LoginFail){
				print("LoginFail");
			} else {
				print("other " + message.getClass().getSimpleName());
			}
		}
	}
	
	/**
     * Invoked when {@link Channel#write(Object)} is called.
     */
//    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//    	log(ctx, e.getMessage(), "writeRequested");
//        ctx.sendDownstream(e);
//    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
    		throws Exception {
    	log(ctx, e.getMessage(), "writeRequested");
    	super.messageReceived(ctx, e);
    	ctx.getChannel().close();
    }
    
//    /**
//     * Invoked when {@link Channel#bind(SocketAddress)} was called.
//     */
//    public void bindRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//    	log(ctx, null, "bindRequested");
//        ctx.sendDownstream(e);
//    }
//
//    /**
//     * Invoked when {@link Channel#connect(SocketAddress)} was called.
//     */
//    public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//    	log(ctx, null, "connectRequested");
//        ctx.sendDownstream(e);
//    }
//
//    /**
//     * Invoked when {@link Channel#setInterestOps(int)} was called.
//     */
//    public void setInterestOpsRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//    	log(ctx, null, "setInterestOpsRequested");
//    	ctx.sendDownstream(e);
//    }
//
//    /**
//     * Invoked when {@link Channel#disconnect()} was called.
//     */
//    public void disconnectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//    	log(ctx, null, "disconnectRequested");
//    	ctx.sendDownstream(e);
//    }
//
//    /**
//     * Invoked when {@link Channel#unbind()} was called.
//     */
//    public void unbindRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//    	log(ctx, null, "unbindRequested");
//    	ctx.sendDownstream(e);
//    }
//
//    /**
//     * Invoked when {@link Channel#close()} was called.
//     */
//    public void closeRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//    	log(ctx, null, "closeRequested");
//    	ctx.sendDownstream(e);
//    }
	
	private void print(String msg){
		System.out.println(System.currentTimeMillis() + " " + this.getClass().getSimpleName() + " : " + msg);
	}

}