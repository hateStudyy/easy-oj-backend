package com.coldwind.easyoj.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 * @author ckl
 * @since 2023/12/22 15:26
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;
    /**
     * 输出用例
     */
    private String output;
}
