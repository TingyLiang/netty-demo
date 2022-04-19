package demo.lty.netty.server;

import demo.lty.netty.server.handler.PersonDecoder;
import demo.lty.netty.server.handler.ServerChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NioServer {

    private int port;

    public NioServer(int port) {
        this.port = port;
    }

    public void start() {
        // boss group 负责接受连接, worker 负责具体的业务处理
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() { //
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new PersonDecoder(), new ServerChannelHandler());// 添加自定义的handler处理逻辑
                    }
                }).option(ChannelOption.SO_BACKLOG, 128)// 服务端可连接队列长度， 已连接+未连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);
        try {
            ChannelFuture f = bootstrap.bind(port).sync();// bind & start
            System.out.println("server started on port " + port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
