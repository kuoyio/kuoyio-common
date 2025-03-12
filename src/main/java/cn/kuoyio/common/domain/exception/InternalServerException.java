package cn.kuoyio.common.domain.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends DomainException {

    public InternalServerException(String code, String message, Throwable cause, Object data) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code, message, null, cause, data);
    }

    public InternalServerException(String code, String message) {
        this(code, message, null, null);
    }

    public InternalServerException(String code, String message, Throwable cause) {
        this(code, message, cause, null);
    }
}