package com.shok.eoj.judge.codesandbox.impl;

import com.shok.eoj.judge.codesandbox.CodeSandbox;
import com.shok.eoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.shok.eoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱
 */
public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
