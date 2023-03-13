package com.example.digitalriskprevention.common;

import com.example.digitalriskprevention.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 公共返回类
 * 页面返回结果封装
 *
 * @ClassName Result
 * @Author zhangwentao
 * @Date 2023/3/2 16:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    /**
     * 是否成功
     **/
    private Boolean isSuccess;
    /**
     * 错误信息
     **/
    private String message;
    /**
     * 请求状态 200-成功 400-失败
     **/
    private Integer code;
    /**
     * 当前时间戳
     **/
    private Long timestamp;
    /**
     * 返回结果
     **/
    private Object data;

    /**
     * 数据量
     *
     * @return
     */
    private Integer total;

    public static Result ok() {
        return new Result(true, ResultCodeEnum.SUCCESS.getMessage(), ResultCodeEnum.SUCCESS.getCode(), System.currentTimeMillis(), null, null);
    }

    public static Result ok(Object data) {
        return new Result(true, ResultCodeEnum.SUCCESS.getMessage(), ResultCodeEnum.SUCCESS.getCode(), System.currentTimeMillis(), data, null);
    }

    public static Result ok(List<?> data, Integer total) {
        return new Result(true, ResultCodeEnum.SUCCESS.getMessage(), ResultCodeEnum.SUCCESS.getCode(), System.currentTimeMillis(), data, total);
    }

    public static Result fail(String errorMsg) {
        return new Result(false, StringUtils.isBlank(errorMsg) ? ResultCodeEnum.ERROR.getMessage() : errorMsg, ResultCodeEnum.ERROR.getCode(), System.currentTimeMillis(), null, null);
    }
}
