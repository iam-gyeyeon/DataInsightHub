package com.project.datainsight.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponse<T> {
    private String message;
    private int code;
    private T data;

    // 생성자
    public CommonResponse(String message, int code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    // Getter 및 Setter 생략
}
