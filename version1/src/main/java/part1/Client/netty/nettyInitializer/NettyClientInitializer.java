package part1.Client.netty.nettyInitializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

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
    protected void initChannel(SocketChannel ch) throws Exception{

    }

}
