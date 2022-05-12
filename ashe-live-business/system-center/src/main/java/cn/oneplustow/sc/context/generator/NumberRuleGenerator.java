package cn.oneplustow.sc.context.generator;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.enume.PaddingType;
import cn.oneplustow.common.enume.RuleType;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.context.ResetFactory;
import cn.oneplustow.sc.context.Resetor;
import cn.oneplustow.sc.entity.SeqRule;
import cn.oneplustow.sc.entity.SeqRuleLinkage;
import cn.oneplustow.sc.service.ISeqRuleLinkageService;
import cn.oneplustow.sc.service.ISeqRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数值规则值生成
 * @author cc
 */
@Component
public class NumberRuleGenerator extends BaseRuleGenerator {

    @Autowired
    private ResetFactory resetFactory;

    @Autowired
    private ISeqRuleService seqRuleService;

    @Autowired
    private ISeqRuleLinkageService seqRuleLinkageService;

    @Override
    @Transactional(readOnly = false,rollbackFor = {Exception.class})
    public String generateNumber(GenerateContext generateContext) {
        SeqRule rule = generateContext.getSeqRule();
        Long number = getNumber(generateContext);
        String result = polishing(rule, String.valueOf(number));
        return result;
    }

    private boolean isGenerate(SeqRule rule,Map<String,Object> param) {
        if(param == null || ObjectUtil.isNull(param.get(rule.getRuleOrder().toString()))){
            return true;
        }
        return (Boolean) param.get(rule.getRuleOrder().toString());
    }

    protected Long getNumber(GenerateContext generateContext) {
        SeqRule rule = generateContext.getSeqRule();

        boolean resetFlag = false;
        //如果重新信息不为空，则判断相关重置
        if(rule.getResetType() != null){
            Resetor resetor = resetFactory.get(rule.getResetType());
            if(resetor != null){
                resetFlag = resetor.reset(rule);
            }
        }

        String ruleValue = rule.getRuleValue();
        SeqRuleLinkage seqRuleLinkage = null;
        Long currentNo = rule.getCurrentNo();
        if(StrUtil.isNotBlank(ruleValue)) {
            List<String> linkageValue = StrUtil.split(ruleValue,',');
            Map<Integer, String> seqStringMap = generateContext.getSeqStringMap();
            String linkageKey = linkageValue.stream().map(Integer::parseInt).map(i -> seqStringMap.get(i)).collect(Collectors.joining("-"));
            seqRuleLinkage = new SeqRuleLinkage(rule.getId(),rule.getSeqId(),linkageKey);
            SeqRuleLinkage linkage = seqRuleLinkageService.getLinkage(seqRuleLinkage);
            if(linkage != null){
                seqRuleLinkage = linkage;
                currentNo = linkage.getLinkageValue();
            }
        }

        if (!isGenerate(rule,getParamToMap(generateContext))) {
            return currentNo == null ? rule.getStartNo():currentNo;
        }

        Long startNo = rule.getStartNo();
        if(startNo == null){startNo = 0L;}
        Integer step = rule.getStep();
        if(step == null || step < 1){step = 1;}

        if(currentNo == null){currentNo = startNo-step;}

        //如果进行重置了则当前数值为起始值，为重置则当前数值为起始值加上步进值
        Long number;
        if(resetFlag){
            number = startNo;
        }else{
            number = currentNo + step;
        }
        rule.setStartNo(startNo);
        rule.setStep(step);
        boolean integer;
        if(seqRuleLinkage != null ){
            seqRuleLinkage.setLinkageValue(number);
            integer = seqRuleLinkageService.insertOrUpdate(seqRuleLinkage);
        }else{
            rule.setCurrentNo(number);
            //更新规则的基本信息
            integer = seqRuleService.updateById(rule);
        }

        if(!integer){
            throw new WarningMessageException("更新记录条数小于1,触发乐观锁");
        }
        return number;
    }

    protected String polishing(SeqRule rule, String number) {
        String result = number;
        PaddingType type = rule.getPaddingSide();
        String paddingChar = rule.getPaddingChar();
        Integer width = rule.getPaddingWidth();

        //如果有补齐字符串，则根据补齐信息进行补齐
        if(StrUtil.isNotBlank(paddingChar)){
            if(width < 1){
                throw new WarningMessageException(String.format("id为[%s]的规则值补齐信息配置有误，补齐不为空但长度为空",rule.getId()));
            }
            if(type == PaddingType.LEFT || type == null){
                result = StrUtil.padPre(number, width,paddingChar);
            }else{
                result = StrUtil.padAfter(number, width,paddingChar);
            }
        }
        return result;
    }

    @Override
    protected RuleType getSupportRuleType() {
        return RuleType.NUMBER;
    }

    @Override
    protected String getParamKey() {
        return getSupportRuleType().name();
    }
}
