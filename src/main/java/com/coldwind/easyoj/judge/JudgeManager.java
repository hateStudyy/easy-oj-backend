package com.coldwind.easyoj.judge;


import com.coldwind.easyoj.judge.strategy.DefaultJudgeStrategy;
import com.coldwind.easyoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.coldwind.easyoj.judge.strategy.JudgeContext;
import com.coldwind.easyoj.judge.strategy.JudgeStrategy;
import com.coldwind.easyoj.judge.codesandbox.model.JudgeInfo;
import com.coldwind.easyoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 *
 * @author ckl
 * @since 2024/1/2 21:44
 */
@Service
public class JudgeManager {
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
