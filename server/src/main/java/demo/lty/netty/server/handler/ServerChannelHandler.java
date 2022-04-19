package demo.lty.netty.server.handler;

import demo.lty.model.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;

public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*try {
            System.out.println("received: " + ((ByteBuf) msg).readInt());
        } finally {
            ReferenceCountUtil.release(msg);
        }*/
        System.out.println("received: "+ ((Person)msg).toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(Arrays.toString(cause.getStackTrace()));
    }
}
