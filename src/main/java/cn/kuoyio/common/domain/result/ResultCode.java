package cn.kuoyio.common.domain.result;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS("200", "操作成功"),
    FAILED("500", "操作失败"),
    VALIDATE_FAILED("404", "参数检验失败"),
    UNAUTHORIZED("401", "暂未登录或token已经过期"),
    FORBIDDEN("403", "没有相关权限"),
    
    PARAM_IS_INVALID("1001", "参数无效"),
    PARAM_IS_BLANK("1002", "参数为空"),
    PARAM_TYPE_BIND_ERROR("1003", "参数类型错误"),
    PARAM_NOT_COMPLETE("1004", "参数缺失"),
    
    USER_NOT_LOGGED_IN("2001", "用户未登录"),
    USER_LOGIN_ERROR("2002", "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN("2003", "账号已被禁用"),
    USER_NOT_EXIST("2004", "用户不存在"),
    USER_HAS_EXISTED("2005", "用户已存在"),
    
    BUSINESS_ERROR("3001", "业务逻辑异常"),
    
    SYSTEM_INNER_ERROR("4001", "系统内部错误"),
    
    DATA_NOT_FOUND("5001", "数据不存在"),
    DATA_IS_WRONG("5002", "数据有误"),
    DATA_ALREADY_EXISTED("5003", "数据已存在"),
    
    INTERFACE_INNER_INVOKE_ERROR("6001", "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR("6002", "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT("6003", "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID("6004", "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT("6005", "接口请求超时"),
    INTERFACE_EXCEED_LOAD("6006", "接口负载过高");
    
    private final String code;
    private final String message;
    
    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}