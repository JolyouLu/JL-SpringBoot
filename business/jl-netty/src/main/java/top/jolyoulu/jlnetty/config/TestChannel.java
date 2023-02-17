package top.jolyoulu.jlnetty.config;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import top.jolyoulu.jlnetty.entity.proto.DataInfo;
import top.jolyoulu.jlnetty.protocols.decoder.DelimiterDecode;
import top.jolyoulu.jlnetty.protocols.decoder.FixedLengthDecode;
import top.jolyoulu.jlnetty.protocols.decoder.SubByteBufDecode;
import top.jolyoulu.jlnetty.utils.JlHexUtils;

/**
 * @Author LuZhouJin
 * @Date 2023/2/17
 * 用于测试，可删除
 */
public class TestChannel {
    @Slf4j
    public static class MyProtoServerChannel extends ChannelInitializer<SocketChannel> {

        private final static byte HEAD = 0x7E;
        private final static byte TAIL = 0x0D;

        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            ChannelPipeline pipeline = channel.pipeline();
            //可选解析方式有
            //根据符号拆分数据 new DelimiterDecode((byte) '#')
            //根据长度拆分数据 new FixedLengthDecode(10)
            //根据头尾标识拆分数据 new SubByteBufDecode(HEAD, TAIL)
            pipeline.addLast(new SubByteBufDecode(HEAD, TAIL));
            //测试
            pipeline.addLast(new MsgHandler());
        }

        //测试，使用时删除
        private static class MsgHandler extends SimpleChannelInboundHandler<ByteBuf> {

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                log.info("客户端地址：" + ctx.channel().remoteAddress().toString());
                log.info("客户端消息：" + JlHexUtils.byteBuf2Hex(msg));
            }
        }
    }

    @Slf4j
    public static class WebSocketServerChannel extends ChannelInitializer<SocketChannel> {

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
        private static class MsgHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
                log.info("服务器端收到消息:"+msg.text());
                ctx.writeAndFlush(new TextWebSocketFrame("[服务器端收到消息]: "+msg.text()));
            }
        }
    }

    @Slf4j
    public static class ProtoBufServerChannel extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            //指定对那种对象进行解码
            pipeline.addLast("encoder",new ProtobufEncoder());
            pipeline.addLast("decoderDataInfo",new ProtobufDecoder(DataInfo.Message.getDefaultInstance()));
            //自定义的handler，处理业务逻辑，测试，使用时删除
            pipeline.addLast(new MsgHandler());
        }

        //测试，使用时删除
        private static class MsgHandler extends SimpleChannelInboundHandler<DataInfo.Message> {
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Message msg) throws Exception {
                DataInfo.Message.DataType dataType = msg.getDataType();
                switch (dataType){
                    case RequestType:
                        DataInfo.Request request = msg.getRequest();
                        log.info("Request => code:{} data:{}",request.getCode(),request.getData());
                        break;
                    case ResponseType:
                        DataInfo.Response response = msg.getResponse();
                        log.info("Response => code:{} data:{}",response.getCode(),response.getData());
                        break;
                    default:
                        log.info("未知消息");
                        break;
                }
            }
        }
    }

}
