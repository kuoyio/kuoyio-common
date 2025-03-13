package cn.kuoyio.common.domain.exception;

import org.springframework.http.HttpStatus;

public abstract class DomainException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    private final String defaultMessage;
    private final Object data;

    protected DomainException(
        HttpStatus httpStatus,
        String code,
        String message,
        String defaultMessage,
        Throwable cause,                    
        Object data
    ) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus != null ? httpStatus : HttpStatus.BAD_REQUEST;
        this.data = data;
        this.defaultMessage = defaultMessage;
    }
}