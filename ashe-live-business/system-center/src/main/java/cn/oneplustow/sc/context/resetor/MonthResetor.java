package cn.oneplustow.sc.context.resetor;


import cn.oneplustow.sc.entity.SeqRule;
import org.springframework.stereotype.Component;

/**
 * 按月进行重置
 * @author cc
 */
@Component
public class MonthResetor extends BaseDateTimeResetor {

    private final static String MONTH_RESET_FLAG_FORMAT = "yyyyMM";

    @Override
    public boolean reset(SeqRule rule) {
        return handler(rule,MONTH_RESET_FLAG_FORMAT);
    }
}
