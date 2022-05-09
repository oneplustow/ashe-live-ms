package cn.oneplustow.sc.context.generator;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.enume.RuleType;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.context.RuleGenerator;
import cn.oneplustow.sc.entity.SeqRule;
import org.springframework.stereotype.Component;

/**
 * 随机字符规则值生成
 * @author cc
 */
@Component
public class RandomRuleGenerator extends BaseRuleGenerator implements RuleGenerator {

    private final static String INTEGER_FLAG = "int";
    private final static String LETTER_FLAG = "letter";
    @Override
    public String generateNumber(GenerateContext generateContext) {
        SeqRule rule = generateContext.getSeqRule();
        int length = rule.getPaddingWidth();
        if(length < 1){
            throw new WarningMessageException(String.format("id为[%s]的规则配置有误，随机字符长度为空",rule.getId()));
        }
        String result;
        if(StrUtil.equalsIgnoreCase(rule.getRuleValue(),INTEGER_FLAG)){
            result = RandomUtil.randomNumbers(length);
        }else if(StrUtil.endWithIgnoreCase(rule.getRuleValue(),LETTER_FLAG)){
            result = RandomUtil.randomString(RandomUtil.BASE_CHAR,length);
        }else{
            result = RandomUtil.randomString(length);
        }

        return result;
    }

    @Override
    protected RuleType getSupportRuleType() {
        return RuleType.RANDOM;
    }
}
