package com.example.digitalriskprevention.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "requirement_highest")
public class RequirementHighest {


    private int highestId;

    @TableId
    private int id;

    private String requirementId;

    private String dicVectors;

    private String listSimilar;

    private Double highest;

    private String factory;


}
