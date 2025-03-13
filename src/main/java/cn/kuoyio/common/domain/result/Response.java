package cn.kuoyio.common.domain.result;

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

    public static <T> Response<T> success() {
        return Response.<T>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .build();
    }

    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .data(data)
                .build();
    }

    public static <T> Response<T> success(T data, String message) {
        return Response.<T>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .data(data)
                .message(message)
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
}