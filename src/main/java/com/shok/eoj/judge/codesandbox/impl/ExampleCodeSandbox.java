package com.shok.eoj.judge.codesandbox.impl;

import com.shok.eoj.judge.codesandbox.CodeSandbox;
import com.shok.eoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.shok.eoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.shok.eoj.model.dto.questionsubmit.JudgeInfo;
import com.shok.eoj.model.enums.JudgeInfoMessageEnum;
import com.shok.eoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(1000L);
        judgeInfo.setTime(1000L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
