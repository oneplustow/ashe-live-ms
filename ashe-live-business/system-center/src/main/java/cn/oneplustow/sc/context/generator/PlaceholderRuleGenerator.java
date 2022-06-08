package cn.oneplustow.sc.context.generator;


import cn.hutool.core.util.ObjectUtil;
import cn.oneplustow.common.enume.RuleType;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.entity.SeqRule;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 占位符规则值生成
 * @author cc
 */
@Component
public class PlaceholderRuleGenerator extends BaseRuleGenerator {
    @Override
    public String generateNumber(GenerateContext generateContext) {
        SeqRule rule = generateContext.getSeqRule();
        String placeholder = rule.getRuleValue();
        Map<String, Object> paramToMap = getParamToMap(generateContext);
        if(paramToMap == null || ObjectUtil.isNull(paramToMap.get(placeholder))){
            throw new WarningMessageException("占位符生成规则需要生成参数，发现参数为空 或 参数不是Map类型");
        }
        String result = paramToMap.get(placeholder).toString();
        return result;
    }

    @Override
    protected RuleType getSupportRuleType() {
        return RuleType.PLACEHOLDER;
    }

    @Override
    protected String getParamKey() {
        return getSupportRuleType().name();
    }
}
