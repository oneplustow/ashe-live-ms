package cn.oneplustow.sc.context.generator;

import cn.oneplustow.sc.entity.SeqRule;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author cc
 * @date 2021/2/1 13:50
 */
@Data
public class GenerateContext {
    private SeqRule seqRule;

    private List<SeqRule> seqRuleList;

    private Map<Integer,String> seqStringMap;

    private Map<String,Object> param;

    public GenerateContext(List<SeqRule> seqRuleList, Map<Integer, String> seqStringMap, Map<String, Object> param) {
        this.seqRuleList = seqRuleList;
        this.seqStringMap = seqStringMap;
        this.param = param;
    }
}
