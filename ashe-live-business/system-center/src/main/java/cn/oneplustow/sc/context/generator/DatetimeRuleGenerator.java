package cn.oneplustow.sc.context.generator;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.enume.RuleType;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.entity.SeqRule;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 常量规则值生成
 * @author cc
 */
@Component
public class DatetimeRuleGenerator extends BaseRuleGenerator {
    @Override
    public String generateNumber(GenerateContext generateContext) {
        SeqRule rule = generateContext.getSeqRule();
        String result = DateUtil.format(new Date(),rule.getRuleValue());

        if(StrUtil.isBlank(result)){
            throw new WarningMessageException(String.format("利用id为[%s]的规则的规则值[%s]进行编码生成时结果为空",rule.getId(),rule.getRuleValue()));
        }
        return result;
    }

    @Override
    protected RuleType getSupportRuleType() {
        return RuleType.DATETIME;
    }
}
