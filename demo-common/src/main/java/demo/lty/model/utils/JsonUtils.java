package demo.lty.model.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.Arrays;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static String objToJson(Object obj) {
        try {
            return mapper.writer().writeValueAsString(obj)+ "\n";
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object byteToObj(ByteBuf msg, Class cls) {
        int len = msg.readableBytes();
        if (len < 1) {
            System.out.println("no data got.");
        }
        byte[] bytes = new byte[len];
        msg.readBytes(bytes);
        try {
            return mapper.reader().readValue(new String(bytes), cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
