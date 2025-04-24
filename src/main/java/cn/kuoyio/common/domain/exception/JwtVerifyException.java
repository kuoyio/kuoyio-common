package cn.kuoyio.common.domain.exception;

import cn.kuoyio.common.domain.response.ResponseCode;
import org.springframework.http.HttpStatus;

public class JwtVerifyException extends DomainException {

    public JwtVerifyException(String message) {
        super(HttpStatus.BAD_REQUEST, ResponseCode.JWT_VERIFY_EXCEPTION.getCode(), message, null, null, null);
    }
}