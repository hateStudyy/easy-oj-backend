package com.coldwind.easyoj.judge.codesandbox;

import com.coldwind.easyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.coldwind.easyoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 * @author ckl
 * @since 2024/1/2 3:24
 */
public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
