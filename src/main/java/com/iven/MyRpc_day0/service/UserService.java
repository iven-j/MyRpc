package com.iven.MyRpc_day0.service;


import com.iven.MyRpc_day0.common.User;

public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
    User getUserByUserId(Integer id);
    //新增一个功能
    Integer insertUserId(User user);
}
