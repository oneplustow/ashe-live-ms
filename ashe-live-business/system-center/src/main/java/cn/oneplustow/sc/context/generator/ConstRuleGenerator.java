package cn.oneplustow.sc.context.generator;


import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.enume.PaddingType;
import cn.oneplustow.common.enume.RuleType;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.entity.SeqRule;
import org.springframework.stereotype.Component;

/**
 * 常量规则值生成
 * @author greatmap
 */
@Component
public class ConstRuleGenerator extends BaseRuleGenerator {
    @Override
    public String generateNumber(GenerateContext generateContext) {
        SeqRule rule = generateContext.getSeqRule();
        String result = rule.getRuleValue();

        PaddingType type = rule.getPaddingSide();
        String paddingChar = rule.getPaddingChar();
        Integer width = rule.getPaddingWidth();

        //如果有补齐字符串，则根据补齐信息进行补齐
        if(StrUtil.isNotBlank(paddingChar)){
            if(width < 1){
                throw new WarningMessageException(String.format("id为[%s]的规则值补齐信息配置有误，补齐不为空但长度为空",rule.getId()));
            }
            if(type == PaddingType.LEFT || type == null){
                result = StrUtil.padPre(result, width, paddingChar);
            }else{
                result = StrUtil.padAfter(result, width, paddingChar);
            }
        }
        return result;
    }

    @Override
    protected RuleType getSupportRuleType() {
        return RuleType.CONST;
    }
}
