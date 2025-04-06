package com.shok.eoj.judge;

import com.shok.eoj.judge.strategy.DefaultJudgeStrategy;
import com.shok.eoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.shok.eoj.judge.strategy.JudgeContext;
import com.shok.eoj.judge.strategy.JudgeStrategy;
import com.shok.eoj.model.dto.questionsubmit.JudgeInfo;
import com.shok.eoj.model.entity.QuestionSubmit;
import com.shok.eoj.model.enums.QuestionSubmitLanguageEnum;
import org.springframework.stereotype.Service;

/**
 * 判题管理
 */
@Service
public class JudgeManage {
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if (QuestionSubmitLanguageEnum.JAVA.getValue().equals(language)){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
