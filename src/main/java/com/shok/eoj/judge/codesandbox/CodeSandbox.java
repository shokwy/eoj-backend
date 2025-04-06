package com.shok.eoj.judge.codesandbox;

import com.shok.eoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.shok.eoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
