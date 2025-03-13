package cn.kuoyio.common.domain.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResult<T> {
    private int page;
    private int pageSize;
    private long total;
    private List<T> data;
}