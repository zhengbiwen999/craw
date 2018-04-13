package zbw.netty4.handler.codc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import zbw.netty4.model.Request;

/**
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * |  包头	|  模块号      |  命令号    |   长度     |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * 编码器
 */
public class RequestEncoder extends MessageToByteEncoder<Request>{

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Request request, ByteBuf buffer) throws Exception {
        //写包头、模块、cmd号
        buffer.writeInt(Constant.HEADER_FLAG);
        buffer.writeShort(request.getModule());
        buffer.writeShort(request.getCmd());
        //写数据
        int length = request.getData() == null ? 0 : request.getData().length;
        if(length<0){
            buffer.writeInt(length);
        }else{
            buffer.writeInt(length);
            buffer.writeBytes(request.getData());
        }
    }

}
