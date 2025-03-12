package cn.kuoyio.common.domain.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends DomainException {

    public UnauthorizedException(String code, String message, Throwable cause, Object data) {
        super(HttpStatus.UNAUTHORIZED, code, message, null, cause, data);
    }

    public UnauthorizedException(String code, String message) {
        this(code, message, null, null);
    }

    public UnauthorizedException(String code, String message, Throwable cause) {
        this(code, message, cause, null);
    }
}