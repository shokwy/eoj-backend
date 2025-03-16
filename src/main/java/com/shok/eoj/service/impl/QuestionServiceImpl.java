package com.shok.eoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shok.eoj.model.entity.Question;
import com.shok.eoj.service.QuestionService;
import com.shok.eoj.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author SHOK
* @description 针对表【question(题目表)】的数据库操作Service实现
* @createDate 2025-03-16 16:23:50
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




