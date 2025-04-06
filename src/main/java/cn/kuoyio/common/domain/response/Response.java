package cn.kuoyio.common.domain.response;

import cn.kuoyio.common.domain.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class Response<T> implements Serializable {
    private String code;
    private String message;
    private T data;
    private static final String SUCCESS_MESSAGE = "Success";

    public static <T> Response<T> success() {
        return Response.<T>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .message(SUCCESS_MESSAGE)
                .build();
    }

    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .message(SUCCESS_MESSAGE)
                .data(data)
                .build();
    }

    public static <T> Response<T> error(String code, String message) {
        return Response.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    public static <T> Response<T> error(String code, String message, T data) {
        return Response.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> Response<T> error(DomainException domainException) {
        return Response.<T>builder()
                .code(domainException.getCode())
                .message(domainException.getMessage())
                .data(null)
                .build();
    }
}