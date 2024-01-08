package com.coldwind.easyoj.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ckl
 * @since 2024/1/2 3:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeRequest {
    /**
     * 输入样例
     */
    private List<String> inputList;
    /**
     * 用户代码
     */
    private String code;
    /**
     * 编程语言
     */
    private String language;
}
