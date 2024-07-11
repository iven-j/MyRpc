package part1.Client.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import part1.common.Message.RpcResponse;

public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    /**
 * 该方法负责处理接收到的RPC响应    。
 * 当从服务器接收到响应时，它会将响应与通道关联，以便可以在稍后通过别名读取    。
 * 之后，关闭通道    。
 *
 * @param ctx 通道处理器上下文，用于操作Netty的通道
 * @param response 接收到的RPC响应对象
 * @throws Exception 如果在处理响应时发生任何异常
 */

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
        // 接收到response, 给channel设计别名，让sendRequest里读取response
        AttributeKey<RpcResponse> key = AttributeKey.valueOf("RPCResponse");
        ctx.channel().attr(key).set(response);
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //异常处理
        cause.printStackTrace();
        ctx.close();
    }
}