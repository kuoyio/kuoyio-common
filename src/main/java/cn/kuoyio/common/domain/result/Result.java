package cn.kuoyio.common.domain.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class Result<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        return Result.<T>builder()
                .code(ResultCode.SUCCESS.getCode())
                .build();
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .code(ResultCode.SUCCESS.getCode())
                .data(data)
                .build();
    }

    public static <T> Result<T> error(String code, String message) {
        return Result.<T>builder()
                .code(code)
                .message(message)
                .build();
    }
}