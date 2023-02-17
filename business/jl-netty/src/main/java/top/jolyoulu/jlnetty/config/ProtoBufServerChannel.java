package top.jolyoulu.jlnetty.config;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import lombok.extern.slf4j.Slf4j;
import top.jolyoulu.jlnetty.entity.proto.DataInfo;

/**
 * @Author LuZhouJin
 * @Date 2023/2/17
 */
@Slf4j
public class ProtoBufServerChannel extends ChannelInitializer<SocketChannel> {

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
    private class MsgHandler extends SimpleChannelInboundHandler<DataInfo.Message> {
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
