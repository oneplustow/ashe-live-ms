package cn.oneplustow.sc.context;


import cn.hutool.core.lang.Assert;
import cn.oneplustow.common.enume.RuleType;
import cn.oneplustow.common.exception.WarningMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 规则生成器工厂类
 * @author cc
 */
@Component
public class RuleGeneratorFactory {

    @Autowired
    private List<RuleGenerator> ruleGeneratorList;

    public RuleGenerator get(RuleType type){
        Assert.notNull(type,"规则生成器类型不能为空");

        for (RuleGenerator ruleGenerator : ruleGeneratorList) {
            if (ruleGenerator.support(type)) {
                return ruleGenerator;
            }
        }
        throw new WarningMessageException("未找到对应的规则生成类型");
    }
}
