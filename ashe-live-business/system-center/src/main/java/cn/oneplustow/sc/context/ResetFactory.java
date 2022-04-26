package cn.oneplustow.sc.context;


import cn.hutool.core.lang.Assert;
import cn.oneplustow.common.enume.ResetType;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.context.resetor.DayResetor;
import cn.oneplustow.sc.context.resetor.MonthResetor;
import cn.oneplustow.sc.context.resetor.YearResetor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 业务编码重置器实现类工厂类
 * @author cc
 */
@Component
public class ResetFactory {

    @Autowired
    private DayResetor dayResetor;

    @Autowired
    private MonthResetor monthResetor;

    @Autowired
    private YearResetor yearResetor;

    public Resetor get(ResetType type){
        Assert.notNull(type,"规则重置器类型不能为空");
        Resetor resetor = null;

        switch (type){
            case DAY:
                resetor = dayResetor;
                break;
            case MONTH:
                resetor = monthResetor;
                break;
            case YEAR:
                resetor = yearResetor;
                break;
            default:
                break;
        }

        if(resetor == null){
            throw new WarningMessageException("未找到对应的规则重置类型");
        }
        return resetor;
    }

}
