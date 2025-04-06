package com.shok.eoj.judge;

import com.shok.eoj.model.entity.QuestionSubmit;
import com.shok.eoj.model.vo.QuestionSubmitVO;

/**
 * 判题服务
 */
public interface JudgeService {

    public QuestionSubmit doJudge(long questionSubmitId);
}
