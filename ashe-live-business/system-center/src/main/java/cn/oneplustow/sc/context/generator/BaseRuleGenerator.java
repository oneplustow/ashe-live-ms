package cn.oneplustow.sc.context.generator;


import cn.hutool.core.map.MapUtil;
import cn.oneplustow.common.enume.RuleType;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.context.RuleGenerator;
import cn.oneplustow.sc.entity.SeqRule;

import java.util.Map;

/**
 * 规则生成值基础抽象类
 * @author cc
 */
public abstract class BaseRuleGenerator implements RuleGenerator {

    @Override
    public Boolean support(RuleType ruleType) {
        return ruleType == getSupportRuleType();
    }

    @Override
    public String generate(GenerateContext generateContext) {
        validateParam(generateContext.getSeqRule());
        return generateNumber(generateContext);
    }

    protected Map<String,Object> getParamToMap(GenerateContext generateContext){
        Map<String, Object> param = generateContext.getParam();
        if(MapUtil.isEmpty(param)){return null;}
        Object paramObject = param.get(getParamKey());
        if(paramObject == null || !(paramObject instanceof Map)){
            return null;
        }
        return (Map<String,Object>)paramObject;
    }


    /**
     * 生成编码
     * @param rule
     * @param param
     * @return
     */
    public abstract String generateNumber(GenerateContext generateContext);
    /**
     * 获取支持的规则类型
     * @return
     */
    protected abstract RuleType getSupportRuleType();

    /**
     * 获取特定规则参数
     * @return
     */
    protected String getParamKey(){
        return null;
    }

    protected void validateParam(SeqRule rule){
        if(rule == null){
            throw new WarningMessageException("业务编码规则定义为空");
        }
    }
}
