package com.iven.MyRpc_day0.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RpcResponse implements Serializable {
    //状态码
    private int code;
    //状态信息
    private String message;
    //具体数据
    private Object data;
    //构建成功信息
    public static RpcResponse sussess(Object data){
        return RpcResponse.builder().code(200).data(data).build();
    }
    //构建失败信息
    public static RpcResponse fail(){
        return RpcResponse.builder().code(500).message("服务器发生错误").build();
    }
}
