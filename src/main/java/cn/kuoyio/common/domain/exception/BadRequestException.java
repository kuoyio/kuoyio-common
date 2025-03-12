package cn.kuoyio.common.domain.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends DomainException {

    public BadRequestException(String code, String message, Throwable cause, Object data) {
        super(HttpStatus.BAD_REQUEST, code, message, null, cause, data);
    }

    public BadRequestException(String code, String message) {
        this(code, message, null, null);
    }

    public BadRequestException(String code, String message, Throwable cause) {
        this(code, message, cause, null);
    }
}