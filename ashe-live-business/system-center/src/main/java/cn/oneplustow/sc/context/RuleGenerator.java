package cn.oneplustow.sc.context;


import cn.oneplustow.common.enume.RuleType;
import cn.oneplustow.sc.context.generator.GenerateContext;

/**
 * 规则数据生成器
 * @author cc
 */
public interface RuleGenerator {

    /**
     * 规则生成器，根据规则生成指定的字符串
     * @param rule 规则对象
     * @return 生成的字符串
     */
    String generate(GenerateContext generateContext);

    /**
     * 是否支持该规则
     * @param ruleType
     * @return
     */
    Boolean support(RuleType ruleType);

}
