package com.iven.MyRpc_day0.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class RpcRequest implements Serializable {
    //客户端进行调用时，传入接口名，方法名，参数以及参数类型

    // 服务类名，客户端只知道接口名，在服务端中用接口名指向实现类
    private String interfaceName;
    // 方法名
    private String methodName;
    // 参数列表
    private Object[] params;
    // 参数类型
    private Class<?>[] paramsTypes;
}
