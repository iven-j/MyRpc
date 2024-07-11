package part1.Client.netty.nettyInitializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import part1.Client.netty.handler.NettyClientHandler;
import part1.Client.rpcClient.impl.NettyRpcClient;

/**
 * NettyClientInitializer类，配置netty对消息的处理机制
 * 指定编码器（将消息转为字节数组），解码器（将字节数组转为消息）
 * 指定消息格式，消息长度，解决沾包问题
 * 什么是沾包问题？
 * netty默认底层通过TCP 进行传输，TCP是面向流的协议，接收方在接收到数据时无法直接得知一条消息的具体字节数，不知道数据的界限。由于TCP的流量控制机制，发生沾包或拆包，会导致接收的一个包可能会有多条消息或者不足一条消息，从而会出现接收方少读或者多读导致消息不能读完全的情况发生
 * 在发送消息时，先告诉接收方消息的长度，让接收方读取指定长度的字节，就能避免这个问题
 * 指定对接收的消息的处理handler
 * 注：这里的addLast没有先后顺序，netty通过加入的类实现的接口来自动识别类实现的是什么功能
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //消息格式 【长度】【消息体】， 解决沾包问题
        pipeline.addLast(
                new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4)
        );
        //计算当前待发送消息的长度，写入到前4个字节中,
        //这部分添加了一个LengthFieldPrepender，在发送消息前，它会在消息前添加一个长度字段（4个字节）
        pipeline.addLast(new LengthFieldPrepender(4));

        //使用Java序列化方式,将java对象序列化成字节流，netty的自带的解码编码支持传输这种结构
        pipeline.addLast(new ObjectEncoder());

        //使用了Netty中的ObjectDecoder，它用于将字节流解码为 Java 对象。
        //在ObjectDecoder的构造函数中传入了一个ClassResolver 对象，用于解析类名并加载相应的类。
        pipeline.addLast(new ObjectDecoder(new ClassResolver() {
            @Override
            public Class<?> resolve(String className) throws ClassNotFoundException{
                return Class.forName(className);
            }
        }));
        pipeline.addLast(new NettyClientHandler());
    }

}
