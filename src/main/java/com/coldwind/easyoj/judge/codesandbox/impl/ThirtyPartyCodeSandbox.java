package com.coldwind.easyoj.judge.codesandbox.impl;

import com.coldwind.easyoj.judge.codesandbox.CodeSandbox;
import com.coldwind.easyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.coldwind.easyoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上现有的代码沙箱）
 * @author ckl
 * @since 2024/1/2 3:33
 */
public class ThirtyPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
