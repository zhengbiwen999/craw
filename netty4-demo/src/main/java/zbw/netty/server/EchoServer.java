package zbw.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import zbw.netty.handler.EchoServerHandler;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
        if(args.length != 1){
            System.err.println("端口格式不正确");
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    public void start() throws Exception{
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        System.out.println("服务端启动");
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            //异步绑定服务器，调用sync阻塞等待，直到绑定完成
            ChannelFuture f = bootstrap.bind().sync();
            //获取channel的closeFuture 并且阻塞，直到当前线程完成
            f.channel().closeFuture().sync();
        }finally {
            //关闭EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }

}
