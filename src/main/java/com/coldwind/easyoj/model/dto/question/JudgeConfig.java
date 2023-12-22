package com.coldwind.easyoj.model.dto.question;

import lombok.Data;

/**
 * 题目配置
 * @author ckl
 * @since 2023/12/22 15:26
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制（ms）
     */
    private Long timeLimit;
    /**
     * 内存限制(kb)
     */
    private Long memoryLimit;
    /**
     * 堆栈限制(kb)
     */
    private Long stackLimit;

}
