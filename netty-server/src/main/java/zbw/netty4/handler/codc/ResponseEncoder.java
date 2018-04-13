package zbw.netty4.handler.codc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zbw.netty4.model.Response;

/**
 * 响应结果编码器
 *
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * |  包头	|  模块号      |  命令号    |  结果码    |  长度       |   数据     |
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 */
public class ResponseEncoder extends MessageToByteEncoder<Response>{

    private static final Logger logger = LoggerFactory.getLogger(ResponseEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Response response, ByteBuf buffer) throws Exception {
        logger.info("返回请求:" + "module:" +response.getModule() +" cmd:" + response.getCmd() + " resultCode:" + response.getStateCode());
        //包头
        buffer.writeInt(Constant.HEADER_FLAG);
        buffer.writeShort(response.getModule());
        buffer.writeShort(response.getCmd());
        buffer.writeInt(response.getStateCode());
        //数据长度
        int length = response.getData() == null ? 0 : response.getData().length;
        if(length <0){
            buffer.writeInt(length);
        }else {
            buffer.writeInt(length);
            buffer.writeBytes(response.getData());
        }
    }
}
