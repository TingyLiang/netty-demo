package demo.lty.app;

import demo.lty.netty.client.MessageProducer;

/**
 * Hello world!
 */
public class Client {
    public static void main(String[] args) {
        MessageProducer producer = new MessageProducer("localhost", 8089);
        producer.start();
    }
}
