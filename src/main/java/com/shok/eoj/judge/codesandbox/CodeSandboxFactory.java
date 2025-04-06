package com.shok.eoj.judge.codesandbox;

import com.shok.eoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.shok.eoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.shok.eoj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 静态工厂（代码沙箱工厂，根据字符串参数创建指定的代码沙箱实例）
 */
public class CodeSandboxFactory {
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
