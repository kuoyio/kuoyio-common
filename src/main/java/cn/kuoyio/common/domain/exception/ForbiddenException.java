package cn.kuoyio.common.domain.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends DomainException {

    public ForbiddenException(String code, String message, Throwable cause, Object data) {
        super(HttpStatus.FORBIDDEN, code, message, null, cause, data);
    }

    public ForbiddenException(String code, String message) {
        this(code, message, null, null);
    }

    public ForbiddenException(String code, String message, Throwable cause) {
        this(code, message, cause, null);
    }
}