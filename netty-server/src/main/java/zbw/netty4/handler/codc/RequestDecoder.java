package zbw.netty4.handler.codc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import zbw.netty4.model.Request;

import java.util.List;

/**
 * 数据包解码器
 *
 * http://blog.51cto.com/jfzhang/1361687
 *
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * |  包头	|  模块号      |  命令号    |   长度     |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * 模块号2字节
 * 命令号2字节
 * 长度4字节(数据部分占有字节数量)
 */
public class RequestDecoder extends ByteToMessageDecoder{

    private static int BASE_LENGTH = 4 + 2 + 2 + 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        //轮询一直进行
        while (true){
            //buffer 的可读内容长度 > 基本长度（说明有 data 数据）
            if(buffer.readableBytes() >= BASE_LENGTH){
                //定义第一个可读数据包的起始位置
                int beginIndex;
                while (true){
                    //从可读的index
                    beginIndex = buffer.readerIndex();
                    //标记初始读游标位置（把当前的readerIndex赋值到markReaderIndex中）
                    buffer.markReaderIndex();
                    //读到了包头
                    if(buffer.readInt() == Constant.HEADER_FLAG){
                        break;
                    }
                    //未读到包头标识略过一个字节(重设readerIndex，把markIndex赋值到readerIndex)
                    buffer.resetReaderIndex();
                    buffer.readByte();
                    //不满足
                    if(buffer.readableBytes() < BASE_LENGTH){
                        return;
                    }
                    //读取命令号和cmd
                    short module = buffer.readShort();
                    short cmd = buffer.readShort();
                    //读取数据长度
                    int length = buffer.readInt();
                    if(length<0){
                        ctx.channel().close();
                        return;
                    }
                    //数据包还没有到齐
                    if(buffer.readableBytes() < length){
                        buffer.readerIndex(beginIndex);
                        return;
                    }
                    //读取数据部分
                    byte[] data = new byte[length];
                    buffer.readBytes(data);
                    //封装Request
                    Request message = new Request();
                    message.setModule(module);
                    message.setCmd(cmd);
                    message.setData(data);
                    //解析出消息对象，继续往下面的handler传递
                    out.add(message);
                }
            }else{
                break;
            }
        }
        //数据不完整，等待数据包完整
        return;
    }
}
