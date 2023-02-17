package top.jolyoulu.jlnetty.config;

import io.lettuce.core.StrAlgoArgs;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import top.jolyoulu.jlnetty.protocols.decoder.SubByteBufDecode;
import top.jolyoulu.jlnetty.utils.JlHexUtils;

/**
 * @Author LuZhouJin
 * @Date 2023/2/17
 */
@Slf4j
public class DefServerChannel extends ChannelInitializer<SocketChannel> {

    private final static byte HEAD = 0x7E;
    private final static byte TAIL = 0x0D;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new SubByteBufDecode(HEAD, TAIL));
        //测试
        pipeline.addLast(new MsgHandler());
    }

    //测试，使用时删除
    private class MsgHandler extends SimpleChannelInboundHandler<ByteBuf>{

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            log.info("客户端地址：" + ctx.channel().remoteAddress().toString());
            log.info("客户端消息：" + JlHexUtils.byteBuf2Hex(msg));
        }
    }
}
