package demo.lty.netty.client.handler;

import demo.lty.model.Person;
import demo.lty.model.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

public class PersonEncoder extends MessageToByteEncoder<Person> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Person person, ByteBuf byteBuf) {
        System.out.println("encoding person:" + person);
        byteBuf.writeBytes(JsonUtils.objToJson(person).getBytes(StandardCharsets.UTF_8));
//        channelHandlerContext.writeAndFlush(JsonUtils.objToJson(person).getBytes(StandardCharsets.UTF_8));
    }

}
