package com.coldwind.easyoj.judge.codesandbox;

import com.coldwind.easyoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.coldwind.easyoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.coldwind.easyoj.judge.codesandbox.impl.ThirtyPartyCodeSandbox;

/**
 * 代码沙箱创建工厂（根据字符串参数创建指定的代码沙箱示例）
 *
 * @author ckl
 * @since 2024/1/2 3:47
 */
public class CodeSandboxFactory {

    /**
     * 创建代码沙箱实例
     *
     * @param type 代码沙箱类型
     * @return
     */
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirtyParty":
                return new ThirtyPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
