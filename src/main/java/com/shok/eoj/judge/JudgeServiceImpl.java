package com.shok.eoj.judge;

import cn.hutool.json.JSONUtil;
import com.shok.eoj.common.ErrorCode;
import com.shok.eoj.exception.BusinessException;
import com.shok.eoj.judge.codesandbox.CodeSandbox;
import com.shok.eoj.judge.codesandbox.CodeSandboxFactory;
import com.shok.eoj.judge.codesandbox.CodeSandboxProxy;
import com.shok.eoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.shok.eoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.shok.eoj.judge.strategy.DefaultJudgeStrategy;
import com.shok.eoj.judge.strategy.JudgeContext;
import com.shok.eoj.model.dto.question.JudgeCase;
import com.shok.eoj.model.dto.question.JudgeConfig;
import com.shok.eoj.model.dto.questionsubmit.JudgeInfo;
import com.shok.eoj.model.entity.Question;
import com.shok.eoj.model.entity.QuestionSubmit;
import com.shok.eoj.model.enums.JudgeInfoMessageEnum;
import com.shok.eoj.model.enums.QuestionSubmitLanguageEnum;
import com.shok.eoj.model.enums.QuestionSubmitStatusEnum;
import com.shok.eoj.model.vo.QuestionSubmitVO;
import com.shok.eoj.service.QuestionService;
import com.shok.eoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;
    @Resource
    private QuestionSubmitService questionSubmitService;
    @Value("${codesandbox.type:example}")
    private String type;
    @Resource
    private JudgeManage judgeManage;


    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        //获取信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目正在判题");
        }
        //更改判题状态为判题中
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新异常");
        }
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        //获取输入用例，执行代码
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        ExecuteCodeRequest codeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(codeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        //根据沙箱执行结果，设置判题信息
        DefaultJudgeStrategy defaultJudgeStrategy = new DefaultJudgeStrategy();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManage.doJudge(judgeContext);
        //修改数据库中判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新异常");
        }
        return questionSubmitService.getById(questionSubmitId);
    }
}
