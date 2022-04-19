package demo.lty.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;

public class MessageProduceHandler extends ChannelInboundHandlerAdapter {

   /* @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        final ByteBuf buf = ctx.alloc().buffer(4);
        buf.writeInt(1024);

        final ChannelFuture future = ctx.writeAndFlush(buf);
        future.addListener((ChannelFutureListener) channelFuture -> {
            assert future == channelFuture;
            //  如果write 消息后立马close，可能或造成数据未发送，但连接已关闭，因此，需要等待返回结果
            ctx.close();
        });
        // 如果没有特殊的需求，上述代码可用此行代替
//        future.addListener(ChannelFutureListener.CLOSE);

    }*/

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(Arrays.toString(cause.getStackTrace()));
    }
}
