package cn.kuoyio.common.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("000000"),    
    KUOYIO_API_GATEWAY_PREFIX("01"),    
    KUOYIO_BLOG_SERVICE_PREFIX("02");
    private final String code;
}