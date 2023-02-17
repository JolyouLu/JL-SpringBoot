package top.jolyoulu.jlnetty.client.defserver;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Author LuZhouJin
 * @Date 2023/2/17
 */
public class Client {

    private final static ByteBuf HEAD = Unpooled.copiedBuffer(new byte[]{0x7E});
    private final static ByteBuf TAIL = Unpooled.copiedBuffer(new byte[]{0x0D});

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("localhost", 9999).sync();
            Channel channel = channelFuture.channel();
            //客户端需要输入消息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                ByteBuf cont = Unpooled.copiedBuffer(msg.getBytes(StandardCharsets.UTF_8));
                //通过channel 发生到服务器端
                channel.writeAndFlush(Unpooled.copiedBuffer(HEAD, cont, TAIL));
            }
        }finally {
            group.shutdownGracefully();
        }
    }
}
