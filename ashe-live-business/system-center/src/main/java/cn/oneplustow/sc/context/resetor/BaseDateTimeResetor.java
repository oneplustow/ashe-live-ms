package cn.oneplustow.sc.context.resetor;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.context.Resetor;
import cn.oneplustow.sc.entity.SeqRule;

import java.util.Date;

/**
 * 根据时间进行规则重置的抽象
 * @author cc
 */
public abstract class BaseDateTimeResetor implements Resetor {

    protected boolean handler(SeqRule rule, String resetFlagFormatPattern){

        if(rule == null){
            throw new WarningMessageException("规则配置信息为空");
        }

        if(StrUtil.isBlank(resetFlagFormatPattern)){
            throw new WarningMessageException("重置规则标识格式不能为空");
        }

        String resetFlag = rule.getResetFlag();
        boolean result = false;
        String currentDateFlag = DateUtil.format(new Date(), resetFlagFormatPattern);
        if(StrUtil.isBlank(resetFlag) || !StrUtil.equalsIgnoreCase(currentDateFlag,resetFlag)){
            rule.setResetFlag(currentDateFlag);
            result = true;
            if(rule.getStartNo() == null){
                rule.setStartNo(0L);
                rule.setCurrentNo(0L);
            }else{
                rule.setCurrentNo(rule.getStartNo());
            }
        }
        return result;
    }

}
