package zbw.netty4.server;


import com.google.common.base.Throwables;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zbw.netty4.handler.ServerHandler;

public class ServerRun {

    private static final Logger logger = LoggerFactory.getLogger(ServerRun.class);

    public void start() {
        ServerBootstrap b = new ServerBootstrap();
        //
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();

        try {
            b.group(bossGroup, wokerGroup);
            //设置channel工厂
            b.channel(NioServerSocketChannel.class);

            //设置管道
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new StringEncoder());
//                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new ServerHandler());
                }
            });
            //tcp参数，链接缓冲池大小
            b.option(ChannelOption.SO_BACKLOG, 2048);
            b.bind(10111).sync();
            System.out.println("start!!!!");
            logger.info("start!!!!");
        } catch (Exception e) {
            logger.error("启动失败！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerRun run = new ServerRun();
        run.start();
    }

}
