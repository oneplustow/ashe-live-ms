package cn.oneplustow.sc.context;


import cn.oneplustow.sc.entity.SeqRule;

/**
 * 业务编码规则重置接口
 * @author cc
 */
public interface Resetor {

    /**
     * 对规则进行参数复位
     * @param rule 编码规则
     * @return 是否复位成功
     */
    boolean reset(SeqRule rule);

}
