package com.coldwind.easyoj.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.coldwind.easyoj.common.ErrorCode;
import com.coldwind.easyoj.exception.BusinessException;
import com.coldwind.easyoj.judge.codesandbox.CodeSandbox;
import com.coldwind.easyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.coldwind.easyoj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 * @author ckl
 * @since 2024/1/2 3:33
 */
public class RemoteCodeSandbox implements CodeSandbox {

    // 定义鉴权请求头和密钥
    public static final String AUTH_REQUEST_HEADER = "auth";

    public static final String AUTH_REQUEST_SECRET = "secretKey";
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8090/executeCode";
        String jsonStr = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER,AUTH_REQUEST_SECRET)
                .body(jsonStr)
                .execute()
                .body();
        if(StringUtils.isBlank(responseStr)) {
            throw  new BusinessException(ErrorCode.API_REQUEST_ERROR,"executeCode remoteSandbox error, message = " + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
