package com.example.digitalriskprevention.model.qo;

import lombok.Data;

/**
 * 分页查询对象
 *
 * @ClassName PageQO
 * @Author zhangwentao
 * @Date 2023/3/2 15:16
 */
@Data
public class PageQO {
    /**
     * 页码
     */
    private int current;
    /**
     * 每页数据量
     */
    private int size;
    /**
     * 数据总量
     */
    private int total;
}
