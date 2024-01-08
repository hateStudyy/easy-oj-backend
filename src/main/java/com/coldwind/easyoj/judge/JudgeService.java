package com.coldwind.easyoj.judge;

import com.coldwind.easyoj.model.entity.QuestionSubmit;

/**
 * 判题服务
 * @author ckl
 * @since 2024/1/2 18:57
 */
public interface JudgeService {

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
