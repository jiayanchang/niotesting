package com.jw.nio.netty.login.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ChildChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.WriteCompletionEvent;

import com.jw.nio.netty.login.message.LoginRequest;
import com.jw.nio.netty.login.message.User;

public class LoginRequestHandler extends SimpleChannelUpstreamHandler {

	private User user;
	
	public LoginRequestHandler(User user){
		this.user = user;
	}
	
	public LoginRequestHandler(String username, String password){
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
	}
	
//	@Override
//	public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e)
//			throws Exception {
//		print("connectRequested isConnected : " + ctx.getChannel().isConnected());
//		super.connectRequested(ctx, e);
//	}
//	@Override
//	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
//			throws Exception {
//		print("writeRequested isConnected : " + ctx.getChannel().isConnected());
//		super.writeRequested(ctx, e);
//	}
//	
//	@Override
//	public void closeRequested(ChannelHandlerContext ctx, ChannelStateEvent e)
//			throws Exception {
//		print("closeRequested isConnected : " + ctx.getChannel().isConnected());
//		super.closeRequested(ctx, e);
//	}
//	
//	@Override
//	public void bindRequested(ChannelHandlerContext ctx,
//			ChannelStateEvent e) throws Exception {
//		print("bindRequested isConnected : " + ctx.getChannel().isConnected());
//		super.bindRequested(ctx, e);
//	}
//	
//	@Override
//	public void unbindRequested(ChannelHandlerContext ctx, ChannelStateEvent e)
//			throws Exception {
//		print("unbindRequested isConnected : " + ctx.getChannel().isConnected());
//		super.unbindRequested(ctx, e);
//	}
//	
//	@Override
//	public void disconnectRequested(ChannelHandlerContext ctx,
//			ChannelStateEvent e) throws Exception {
//		print("disconnectRequested isConnected : " + ctx.getChannel().isConnected());
//		super.disconnectRequested(ctx, e);
//	}
	private void loginReq(Channel channel) {
		LoginRequest req = new LoginRequest();
		req.setUser(user);
		print("请求服务器端登陆");
		channel.write(req);
		print("请求服务器端登陆完成");
	}
	
	private void print(String msg) {
		System.out.println(System.currentTimeMillis() + " " + this.getClass().getSimpleName() + " : " + msg);
	}
	
	/**
     * Invoked when a message object (e.g: {@link ChannelBuffer}) was received
     * from a remote peer.
     */
    public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e) throws Exception {
    	print("messageReceived isConnected : " + ctx.getChannel().isConnected());
    	ctx.sendUpstream(e);
    }

    /**
     * Invoked when a {@link Channel} is open, but not bound nor connected.
     */
    public void channelOpen(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    	print("channelOpen isConnected : " + ctx.getChannel().isConnected());
        ctx.sendUpstream(e);
    }

    /**
     * Invoked when a {@link Channel} is open and bound to a local address,
     * but not connected.
     */
    public void channelBound(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    	print("channelBound isConnected : " + ctx.getChannel().isConnected());
        ctx.sendUpstream(e);
    }

    /**
     * Invoked when a {@link Channel} is open, bound to a local address, and
     * connected to a remote address.
     */
    public void channelConnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    	print("channelConnected isConnected : " + ctx.getChannel().isConnected());
    	loginReq(e.getChannel());
    	ctx.sendUpstream(e);
    }

    /**
     * Invoked when a {@link Channel}'s {@link Channel#getInterestOps() interestOps}
     * was changed.
     */
    public void channelInterestChanged(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    	print("channelInterestChanged isConnected : " + ctx.getChannel().isConnected());
        ctx.sendUpstream(e);
    }

    /**
     * Invoked when a {@link Channel} was disconnected from its remote peer.
     */
    public void channelDisconnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    	print("channelDisconnected isConnected : " + ctx.getChannel().isConnected());
        ctx.sendUpstream(e);
    }

    /**
     * Invoked when a {@link Channel} was unbound from the current local address.
     */
    public void channelUnbound(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    	print("channelUnbound isConnected : " + ctx.getChannel().isConnected());
        ctx.sendUpstream(e);
    }

    /**
     * Invoked when a {@link Channel} was closed and all its related resources
     * were released.
     */
    public void channelClosed(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    	print("channelClosed isConnected : " + ctx.getChannel().isConnected());
        ctx.sendUpstream(e);
    }

    /**
     * Invoked when something was written into a {@link Channel}.
     */
    public void writeComplete(
            ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
    	print("writeComplete isConnected : " + ctx.getChannel().isConnected());
        ctx.sendUpstream(e);
    }

    /**
     * Invoked when a child {@link Channel} was open.
     * (e.g. a server channel accepted a connection)
     */
    public void childChannelOpen(
            ChannelHandlerContext ctx, ChildChannelStateEvent e) throws Exception {
    	print("childChannelOpen isConnected : " + ctx.getChannel().isConnected());
        ctx.sendUpstream(e);
    }

    /**
     * Invoked when a child {@link Channel} was closed.
     * (e.g. the accepted connection was closed)
     */
    public void childChannelClosed(
            ChannelHandlerContext ctx, ChildChannelStateEvent e) throws Exception {
    	print("childChannelClosed isConnected : " + ctx.getChannel().isConnected());
        ctx.sendUpstream(e);
    }

    /**
     * Invoked when {@link Channel#setInterestOps(int)} was called.
     */
    public void setInterestOpsRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    	print("setInterestOpsRequested isConnected : " + ctx.getChannel().isConnected());
        ctx.sendDownstream(e);
    }

}
