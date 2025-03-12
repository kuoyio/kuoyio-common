package cn.kuoyio.common.infrastructure.factory;

import cn.kuoyio.common.domain.result.Result;
import cn.kuoyio.common.domain.result.ResultCode;
import lombok.RequiredArgsConstructor;

public class ResultFactory {
    public static <T> Result.ResultBuilder<T> builder() {
        return Result.<T>builder();
    }

    public static <T> Result<T> success() {
        return Result.<T>builder()
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getMessage())
                .build();
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static <T> Result<T> success(String message, T data) {
        return Result.<T>builder()
                .code(ResultCode.SUCCESS.getCode())
                .message(message)
                .data(data)
                .build();
    }
}