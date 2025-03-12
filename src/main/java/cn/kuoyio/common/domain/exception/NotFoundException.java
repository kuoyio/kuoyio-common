package cn.kuoyio.common.domain.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends DomainException {

    public NotFoundException(String code, String message, Throwable cause, Object data) {
        super(HttpStatus.NOT_FOUND, code, message, null, cause, data);
    }

    public NotFoundException(String code, String message) {
        this(code, message, null, null);
    }

    public NotFoundException(String code, String message, Throwable cause) {
        this(code, message, cause, null);
    }
}