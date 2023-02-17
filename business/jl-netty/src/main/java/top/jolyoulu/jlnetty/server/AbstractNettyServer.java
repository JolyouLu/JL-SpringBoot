package top.jolyoulu.jlnetty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @Author LuZhouJin
 * @Date 2023/2/17
 */
@Slf4j
public abstract class AbstractNettyServer {
    //绑定端口
    private Integer port;
    //初始化一个ServerBootstrap
    protected abstract ServerBootstrap init();
    //ServerBootstrap关闭后的操作
    public abstract void shutdown();

    public ChannelFuture start(){
        if (Objects.isNull(port)){
            log.error("未设置port参数",new IllegalArgumentException());
        }
        ServerBootstrap bootstrap = init();
        return bootstrap.bind(port);
    }

    protected void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }
}
