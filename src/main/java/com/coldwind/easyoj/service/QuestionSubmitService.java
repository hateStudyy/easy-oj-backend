package com.coldwind.easyoj.service;

import com.coldwind.easyoj.model.dto.qustionsubmit.QuestionSubmitAddRequest;
import com.coldwind.easyoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coldwind.easyoj.model.entity.User;

/**
* @author 123
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2023-12-22 12:17:51
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
