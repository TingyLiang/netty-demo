package demo.lty.app;

import demo.lty.netty.server.NioServer;

/**
 * Hello world!
 *
 */
public class Server
{
    public static void main( String[] args )
    {
        NioServer server = new NioServer(8089);
        server.start();
    }
}
