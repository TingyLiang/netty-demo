package demo.lty.netty.client;

import demo.lty.model.Person;
import demo.lty.netty.client.handler.MessageProduceHandler;
import demo.lty.netty.client.handler.PersonEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


public class MessageProducer {
    private String host;
    private int port;

    public MessageProducer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        EventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker);
        bootstrap.channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 这里必须添加handler,否则无法发送所有消息到server端
                        socketChannel.pipeline().addLast(new PersonEncoder(), new MessageProduceHandler());
                    }
                });

        try {
            ChannelFuture future = bootstrap.connect(host, port).sync();

            for (int i = 0; i < 100; i++) {
                future.channel().writeAndFlush(new Person("P" + i, 25));
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            worker.shutdownGracefully();
        }
    }
}
