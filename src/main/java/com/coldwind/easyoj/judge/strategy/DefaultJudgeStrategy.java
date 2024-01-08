package com.coldwind.easyoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.coldwind.easyoj.model.dto.question.JudgeCase;
import com.coldwind.easyoj.model.dto.question.JudgeConfig;
import com.coldwind.easyoj.judge.codesandbox.model.JudgeInfo;
import com.coldwind.easyoj.model.entity.Question;
import com.coldwind.easyoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * @author ckl
 * @since 2024/1/2 21:04
 */
public class DefaultJudgeStrategy implements JudgeStrategy {
    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);

        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        if (outputList.size() != inputList.size()) {
            // 答案错误并返回
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            return null;
        }
        // 对比输出用例与结果
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                return null;
            }
        }
        // 判断题目限制
        JudgeConfig judgeConfig = JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class);
        Long needTimeLimit = judgeConfig.getTimeLimit();
        Long needMemoryLimit = judgeConfig.getMemoryLimit();
        if (memory > needMemoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            return null;
        }
        if (time > needTimeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            return null;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
