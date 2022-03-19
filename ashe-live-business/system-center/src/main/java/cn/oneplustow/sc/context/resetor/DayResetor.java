package cn.oneplustow.sc.context.resetor;


import cn.oneplustow.sc.entity.SeqRule;
import org.springframework.stereotype.Component;

/**
 * 按天进行重置
 * @author cc
 */
@Component
public class DayResetor extends BaseDateTimeResetor {

    private final static String DAY_RESET_FLAG_FORMAT = "yyyyMMdd";

    @Override
    public boolean reset(SeqRule rule) {
        return handler(rule,DAY_RESET_FLAG_FORMAT);
    }

}
