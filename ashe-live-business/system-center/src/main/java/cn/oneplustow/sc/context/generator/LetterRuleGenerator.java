package cn.oneplustow.sc.context.generator;


import cn.oneplustow.common.enume.RuleType;
import cn.oneplustow.common.util.StringUtils;
import cn.oneplustow.sc.entity.SeqRule;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 2021/1/27 10:59
 */
@Component
public class LetterRuleGenerator extends NumberRuleGenerator {

    @Override
    public String generateNumber(GenerateContext generateContext) {
        SeqRule rule = generateContext.getSeqRule();
        Long number = super.getNumber(generateContext);
        String letter = StringUtils.numberToLetter(number);
        return super.polishing(rule,letter);
    }

    @Override
    protected RuleType getSupportRuleType() {
        return RuleType.LETTER;
    }

    @Override
    protected String getParamKey() {
        return getSupportRuleType().name();
    }

}
