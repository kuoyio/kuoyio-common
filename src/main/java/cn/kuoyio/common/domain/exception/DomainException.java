package cn.kuoyio.common.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
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

    protected DomainException(
            String code,
            String message
    ) {
        super(message, null);
        this.code = code;
        this.message = message;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.data = null;
        this.defaultMessage = null;
    }

    protected DomainException(
        String code,
        String message,
        Throwable cause
    ) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.data = null;
        this.defaultMessage = null;
    }
}