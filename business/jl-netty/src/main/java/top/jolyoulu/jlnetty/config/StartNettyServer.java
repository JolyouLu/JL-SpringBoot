package top.jolyoulu.jlnetty.config;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.jolyoulu.jlnetty.protocols.decoder.SubByteBufDecode;
import top.jolyoulu.jlnetty.server.AbstractNettyServer;
import top.jolyoulu.jlnetty.server.MyProtoServer;
import top.jolyoulu.jlnetty.server.ProtoBufServer;
import top.jolyoulu.jlnetty.server.WebSocketServer;
import top.jolyoulu.jlnetty.utils.JlHexUtils;

/**
 * @Author LuZhouJin
 * @Date 2023/2/17
 */
@Slf4j
@Component
public class StartNettyServer implements CommandLineRunner, DisposableBean {

    //springboot 关闭前执行
    @Override
    public void run(String... args) {
//        AbstractNettyServer server = new MyProtoServer(9999, new TestChannel.MyProtoServerChannel());
//        AbstractNettyServer server = new WebSocketServer(9999, new TestChannel.WebSocketServerChannel());
        AbstractNettyServer server = new ProtoBufServer(9999, new TestChannel.ProtoBufServerChannel());
        try {
            //初始化一个nettyServer
            ChannelFuture channelFuture = server.start();
            //通道绑定通知
            ChannelFuture cf = channelFuture.sync();
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) {
                    if (cf.isSuccess()) {
                        log.info("Netty服务，绑定{}端口成功", server.getPort());
                    } else {
                        log.info("Netty服务，绑定{}端口失败", server.getPort());
                    }
                }
            });
            //通道关闭通知
            ChannelFuture sync = cf.channel().closeFuture().sync();
            sync.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) {
                    log.info("Netty服务，已经关闭");
                }
            });
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } finally {
            server.shutdown();
        }
    }

    //springboot 启动成功后执行
    @Override
    public void destroy() {

    }
}
