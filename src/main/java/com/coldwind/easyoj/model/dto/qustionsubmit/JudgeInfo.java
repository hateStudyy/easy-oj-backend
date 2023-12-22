package com.coldwind.easyoj.model.dto.qustionsubmit;

import lombok.Data;

/**
 * 判题信息
 * @author ckl
 * @since 2023/12/22 15:26
 */
@Data
public class JudgeInfo {
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
