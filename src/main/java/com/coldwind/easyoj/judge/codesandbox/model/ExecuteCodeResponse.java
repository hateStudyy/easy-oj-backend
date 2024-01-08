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
public class ExecuteCodeResponse {
    /**
     * 输出信息
     */
    private List<String> outputList;
    /**
     * 接口信息
     */
    private String message;
    /**
     * 执行状态
     */
    private Integer status;
    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
