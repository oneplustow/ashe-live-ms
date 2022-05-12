package cn.oneplustow.sc.context.resetor;


import cn.oneplustow.sc.entity.SeqRule;
import org.springframework.stereotype.Component;

/**
 * 按年进行重置
 * @author cc
 */
@Component
public class YearResetor extends BaseDateTimeResetor {

    private final static String DAY_RESET_FLAG_FORMAT = "yyyy";

    @Override
    public boolean reset(SeqRule rule) {
        return handler(rule, DAY_RESET_FLAG_FORMAT);
    }
}
