package com.coldwind.easyoj.judge.codesandbox;

import com.coldwind.easyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.coldwind.easyoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 示例代码沙箱
 *
 * @author ckl
 * @since 2024/1/2 3:33
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息" + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
