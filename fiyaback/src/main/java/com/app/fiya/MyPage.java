package com.app.fiya;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record MyPage<T>(List<T> content, Integer size, Long elements, Integer page, boolean firs, boolean last) {
    public static <T> MyPage<T> of (Page<T> page){
        return MyPage.<T>builder()
                .content(page.getContent())
                .size(page.getPageable().getPageSize())
                .elements(page.getTotalElements())
                .page(page.getPageable().getPageNumber())
                .firs(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
