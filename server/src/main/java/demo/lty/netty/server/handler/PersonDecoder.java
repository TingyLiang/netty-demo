package demo.lty.netty.server.handler;

import demo.lty.model.Person;
import demo.lty.model.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PersonDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Person p = (Person) JsonUtils.byteToObj(byteBuf, Person.class);
        System.out.println(" going to add person into list: "+p.toString());
        list.add(p);
    }
}
