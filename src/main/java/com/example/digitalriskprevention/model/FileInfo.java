package com.example.digitalriskprevention.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * @Author: zhangwentao
 * @CreateTime: 2023/2/23
 * @Description: 文件信息
 * @Version: 1.0
 */
@Data
@TableName(value = "file_info")
public class FileInfo {
    /**
     * 主键id
     */
    @TableId
    String id;
    /**
     * 文件名
     */
    String fileName;
    /**
     * 上传时间
     */
    Date uploadDate;
    /**
     * IP 记录上传文件的IP
     */
    String ip;
    /**
     * 文件大小（字节）
     */
    long size;

    /**
     * 上传状态 1：成功 0：失败 2:部分成功
     */
    Integer status;

    /**
     * 记录文件处理结果描述信息
     */
    @TableField(jdbcType = JdbcType.CLOB)
    String description;

    public FileInfo(String id) {
        this.id = id;
    }
}
