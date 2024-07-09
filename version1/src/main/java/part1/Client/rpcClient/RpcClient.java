package part1.Client.rpcClient;

import part1.common.Message.RpcRequest;
import part2.common.Message.RpcResponse;

public interface RpcClient {

    //定义底层通信方法
    RpcResponse sendRequesr(RpcRequest request);

    RpcResponse sendRequest(part2.common.Message.RpcRequest request);
}
