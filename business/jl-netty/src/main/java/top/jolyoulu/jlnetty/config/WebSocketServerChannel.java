package top.jolyoulu.jlnetty.config;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import top.jolyoulu.jlnetty.utils.JlHexUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Base64;

/**
 * @Author LuZhouJin
 * @Date 2023/2/17
 */
@Slf4j
public class WebSocketServerChannel extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //因为基于http协议，使用http编码和解码器
        pipeline.addLast(new HttpServerCodec());
        //是以块的方式写的，添加chunkedWriteHandler处理器
        pipeline.addLast(new ChunkedWriteHandler());
        //因为http数据在传输过程中是分段的，HttpObjectAggregator可以将多个段聚合起来
        //这就是为什么当浏览器发送大量数据时，就会发多次http请求
        pipeline.addLast(new HttpObjectAggregator(8192));
        //对应WebSocket的数据是以，帧(frame)形式传递
        //可以看到WebSocketFrame 下有6个子类
        //浏览器请求时 ws://localhost:9999/xxx 表示请求的rui
        //WebSocketServerProtocolHandler 核心功能将来http协议升级为ws协议(WebSocket)，长连接
        pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
        //自定义的handler，处理业务逻辑，测试，使用时删除
        pipeline.addLast(new MsgHandler());
    }

    //测试，使用时删除
    private class MsgHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
            log.info("服务器端收到消息:"+msg.text());
            ctx.writeAndFlush(new TextWebSocketFrame("[服务器端收到消息]: "+msg.text()));
        }
    }
}
