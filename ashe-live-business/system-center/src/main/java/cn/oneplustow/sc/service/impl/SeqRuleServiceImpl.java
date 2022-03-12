package cn.oneplustow.sc.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.enume.PaddingType;
import cn.oneplustow.common.enume.RecycledType;
import cn.oneplustow.common.enume.ResetType;
import cn.oneplustow.common.enume.RuleType;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.entity.SeqRule;
import cn.oneplustow.sc.mapper.SeqRuleMapper;
import cn.oneplustow.sc.service.ISeqRuleService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 编码规则 服务接口类
 * </p>
 *
 * @author greatmap
 * @since 2017-08-10
 */
@Service("seqRuleService")
public class SeqRuleServiceImpl extends ServiceImpl<SeqRuleMapper, SeqRule> implements ISeqRuleService {

    @Autowired
    private SeqRuleMapper seqRuleMapper;

    @Override
    public SeqRule insertOrUpdateRule(SeqRule seqRule) {
        if (ObjectUtil.isNotNull(seqRule.getId())) {
            seqRuleMapper.updateById(seqRule);
            return seqRule;
        } else {
            //判断同一编码ID下是否有重复的规则顺序
            QueryWrapper<SeqRule> w = new QueryWrapper<>();
            w.eq("SEQ_ID",seqRule.getSeqId());
            List<SeqRule> rules = seqRuleMapper.selectList(w);
            if (CollectionUtil.isNotEmpty(rules)) {
                for (SeqRule r : rules) {
                    if (r.getRuleOrder().equals(seqRule.getRuleOrder())) {
                        throw new WarningMessageException("该编码ID下已存在此排序号!");
                    } 
                }
            }
            seqRuleMapper.insert(seqRule);
            return seqRule;
        }
    }

    @Override
    public boolean delSeqRule(List<String> ids) {
        seqRuleMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public IPage<SeqRule> getPage(Page<SeqRule> page, String seqId) {
        QueryWrapper<SeqRule> w = new QueryWrapper<>();
        if (StrUtil.isNotBlank(seqId)) {
            w.eq("SEQ_ID", seqId);
        }
        IPage<SeqRule> seqRuleIPage = seqRuleMapper.selectPage(page, w);
        return seqRuleIPage;
    }

    @Override
    public JSONObject selectEnums() {
    	JSONObject object=new JSONObject();
    	object.put("PaddingType",  Arrays.stream(PaddingType.values()).map(paddingType -> {
	         JSONObject o =new JSONObject();
	         o.put(paddingType.toString(), paddingType.getValue());
	         return o;
	         }).collect(Collectors.toCollection(JSONArray::new)));

        object.put("RecycledType",  Arrays.stream(RecycledType.values()).map(recycledType -> {
	         JSONObject o =new JSONObject();
	         o.put(recycledType.toString(), recycledType.getValue());
	         return o;
	         }).collect(Collectors.toCollection(JSONArray::new)));
        object.put("ResetType",  Arrays.stream(ResetType.values()).map(resetType -> {
	         JSONObject o =new JSONObject();
	         o.put(resetType.toString(), resetType.getValue());
	         return o;
	         }).collect(Collectors.toCollection(JSONArray::new)));
        object.put("RuleType",  Arrays.stream(RuleType.values()).map(ruleType -> {
	         JSONObject o =new JSONObject();
	         o.put(ruleType.toString(), ruleType.getValue());
	         return o;
	         }).collect(Collectors.toCollection(JSONArray::new)));
        return object;
    }
    
}
