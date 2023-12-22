package com.coldwind.easyoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coldwind.easyoj.common.ErrorCode;
import com.coldwind.easyoj.exception.BusinessException;
import com.coldwind.easyoj.model.dto.qustionsubmit.QuestionSubmitAddRequest;
import com.coldwind.easyoj.model.entity.Question;
import com.coldwind.easyoj.model.entity.QuestionSubmit;
import com.coldwind.easyoj.model.entity.QuestionSubmit;
import com.coldwind.easyoj.model.entity.User;
import com.coldwind.easyoj.service.QuestionService;
import com.coldwind.easyoj.service.QuestionSubmitService;
import com.coldwind.easyoj.service.QuestionSubmitService;
import com.coldwind.easyoj.mapper.QuestionSubmitMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author 123
* @description 针对表【question_submit(题目提交表)】的数据库操作Service实现
* @createDate 2023-12-22 12:17:51
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{

    @Resource
    private QuestionService questionService;

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //todo 校验编程语言
        long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交题目
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setLanguage(questionSubmitAddRequest.getLanguage());
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        // todo 设置状态
        // questionSubmit.setStatus();
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setUserId(userId);
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if(!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据插入失败");
        }
        return questionSubmit.getId();
    }

}




