package com.shok.eoj.judge.strategy;

import com.shok.eoj.model.dto.question.JudgeCase;
import com.shok.eoj.model.dto.questionsubmit.JudgeInfo;
import com.shok.eoj.model.entity.Question;
import com.shok.eoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 判题策略传递的参数
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
