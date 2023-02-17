package top.jolyoulu.jlnetty.client.protobufserver;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import top.jolyoulu.jlnetty.entity.proto.DataInfo;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Author LuZhouJin
 * @Date 2023/2/17
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入编码器ProtobufEncoder
                            pipeline.addLast("encoder",new ProtobufEncoder());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("localhost", 9999).sync();
            Channel channel = channelFuture.channel();
            //客户端需要输入消息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                //通过channel 发生到服务器端
                DataInfo.Request req = DataInfo.Request.newBuilder().setCode(200).setData(msg).build();
                DataInfo.Message build = DataInfo.Message.newBuilder().setDataType(DataInfo.Message.DataType.RequestType)
                        .setRequest(req).build();
                channel.writeAndFlush(build);
            }
        }finally {
            group.shutdownGracefully();
        }
    }
}
