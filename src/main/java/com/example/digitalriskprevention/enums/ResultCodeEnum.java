package com.example.digitalriskprevention.enums;

import lombok.Getter;

/**
 * 返回状态枚举
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "success"),
    ERROR(400, "fail"),
    ;
    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
