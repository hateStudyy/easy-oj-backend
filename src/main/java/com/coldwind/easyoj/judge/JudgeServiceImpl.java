package com.coldwind.easyoj.judge;

import cn.hutool.json.JSONUtil;
import com.coldwind.easyoj.common.ErrorCode;
import com.coldwind.easyoj.exception.BusinessException;
import com.coldwind.easyoj.judge.codesandbox.CodeSandbox;
import com.coldwind.easyoj.judge.codesandbox.CodeSandboxFactory;
import com.coldwind.easyoj.judge.codesandbox.CodeSandboxProxy;
import com.coldwind.easyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.coldwind.easyoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.coldwind.easyoj.judge.strategy.JudgeContext;
import com.coldwind.easyoj.model.dto.question.JudgeCase;
import com.coldwind.easyoj.judge.codesandbox.model.JudgeInfo;
import com.coldwind.easyoj.model.entity.Question;
import com.coldwind.easyoj.model.entity.QuestionSubmit;
import com.coldwind.easyoj.model.enums.QuestionSubmitStatusEnum;
import com.coldwind.easyoj.service.QuestionService;
import com.coldwind.easyoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ckl
 * @since 2024/1/2 19:03
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;
    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codeSandbox.type:example}")
    private String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }

        Integer status = questionSubmit.getStatus();
        // 如果不为等待状态
        if (!status.equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题！");
        }
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        boolean updateById = questionSubmitService.updateById(questionSubmitUpdate);
        if (!updateById) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误！");
        }


        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        String judgeCaseStr = question.getJudgeCase();
        // 题目的判题样例
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());

        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        // 5) 根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 6) 修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误！");
        }
        return questionSubmitService.getById(questionSubmitId);
    }
}
