package zbw.netty4.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.Arrays;

public class ServerHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有新连接进来了！！！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("出异常了");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf msg1 = (ByteBuf) msg;
        int readerIndex = msg1.readerIndex();
        int writerIndex = msg1.writerIndex();
        System.out.println("read and write Index is : "+ readerIndex +" --- "+writerIndex);
        byte b = msg1.readByte();
        System.out.println("读取的一个字节是："+(char)b);
        //读取一个字节后，readIndex由 0 变为 1
        System.out.println("读取一个字节后："+msg1.readerIndex());
        msg1.writeByte(1);
        System.out.println("写入一个后："+msg1.writerIndex());
        System.out.println(msg1.toString(Charset.forName("UTF-8")));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开连接了。。。。");
    }
}
