package com.shok.eoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shok.eoj.model.dto.question.QuestionQueryRequest;
import com.shok.eoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.shok.eoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.shok.eoj.model.entity.Question;
import com.shok.eoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shok.eoj.model.entity.User;
import com.shok.eoj.model.vo.QuestionSubmitVO;
import com.shok.eoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author SHOK
* @description 针对表【qustion_submit(题目提交表)】的数据库操作Service
* @createDate 2025-03-16 16:24:10
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 校验
     *
     * @param questionSubmit
     * @param add
     */
    void validQuestionSubmit(QuestionSubmit questionSubmit, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
