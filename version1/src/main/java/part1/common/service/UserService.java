package part1.common.service;


import part1.common.pojo.User;

/**
 * @author 19
 * @version 1.0
 * @create 2024/7/2
 */
public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
    User getUserByUserId(Integer id);
    //新增一个功能
    Integer insertUserId(User user);
}
