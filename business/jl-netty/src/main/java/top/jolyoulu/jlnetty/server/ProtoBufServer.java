package top.jolyoulu.jlnetty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Author LuZhouJin
 * @Date 2023/2/17
 * 一个谷歌Protobuf协议的NettyServer
 */
public class ProtoBufServer extends AbstractNettyServer {

    private final ChannelInitializer<SocketChannel> channel;
    private final EventLoopGroup boss = new NioEventLoopGroup();
    private final EventLoopGroup worker = new NioEventLoopGroup();

    public ProtoBufServer(int port, ChannelInitializer<SocketChannel> channel) {
        super.setPort(port);
        this.channel = channel;
    }

    @Override
    protected ServerBootstrap init() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(channel);
        return bootstrap;
    }

    @Override
    public void shutdown() {
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
