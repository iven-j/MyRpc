package part1.Server.server;

/**
 * @author 19
 * @version 1.0
 * @create 2024/7/2
 */
public interface RpcServer {
    //开启监听
    void start(int port);
    void stop();
}
