package part1.Server;


import part1.Server.server.RpcServer;
import part1.Server.server.impl.NettyRpcServer;
import part1.common.service.Impl.UserServiceImpl;
import part1.common.service.UserService;
import part1.Server.server.impl.SimpleRPCRPCServer;
import part1.Server.provider.ServiceProvider;

/**
 * @author wxx
 * @version 1.0
 * @create 2024/2/11 19:39
 */
public class TestServer {
    public static void main(String[] args) {
        UserService userService=new UserServiceImpl();

        ServiceProvider serviceProvider=new ServiceProvider("127.0.0.1",9999);
        serviceProvider.provideServiceInterface(userService);

        RpcServer rpcServer=new NettyRpcServer(serviceProvider);
        rpcServer.start(9999);
    }
}
