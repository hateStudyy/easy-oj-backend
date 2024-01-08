package com.coldwind.easyoj.judge.codesandbox.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 判题信息
 * @author ckl
 * @since 2023/12/22 15:26
 */
@Data
public class JudgeInfo implements Serializable {
    /**
     * 程序执行信息
     */
    private String message;
    /**
     * 内存消耗(kb)
     */
    private Long memory;
    /**
     * 时间消耗(kb)
     */
    private Long time;

}
