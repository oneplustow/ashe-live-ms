package cn.oneplustow.sc.context;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.enume.RecycledType;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.context.generator.GenerateContext;
import cn.oneplustow.sc.entity.SeqHistory;
import cn.oneplustow.sc.entity.SeqRecycle;
import cn.oneplustow.sc.entity.SeqRule;
import cn.oneplustow.sc.entity.Sequence;
import cn.oneplustow.sc.exception.TriggerOptimisticLockException;
import cn.oneplustow.sc.mapper.SeqHistoryMapper;
import cn.oneplustow.sc.mapper.SeqRecycleMapper;
import cn.oneplustow.sc.mapper.SeqRuleMapper;
import cn.oneplustow.sc.mapper.SequenceMapper;
import cn.oneplustow.sc.service.ISequenceContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 序列号生成对象
 * @author cc
 */
@Service("sequenceContext")
public class SequenceContext implements ISequenceContext {

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private SeqRuleMapper seqRuleMapper;

    @Autowired
    private SeqHistoryMapper seqHistoryMapper;

    @Autowired
    private SeqRecycleMapper seqRecycleMapper;

    @Autowired
    private RuleGeneratorFactory ruleGeneratorFactory;


    /**
     *{@inheritDoc}
     *<br />FIXME 进行线程安全的改造，防止串号
     */
    @Override
    public String apply(String sequenceName, TenantInfo tenantInfo){
        return apply(sequenceName,null,tenantInfo);
    }

    /**
     *{@inheritDoc}
     *<br />FIXME 进行线程安全的改造，防止串号
     */
    @Override
    public String apply(String sequenceName, Map<String, Object> param, TenantInfo tenantInfo){
        Sequence sequence = getSequenceByName(sequenceName);

        String result = null;

        //序列可回收，则从回收信息中查询可用序列,并更新回收信息
        if(sequence.getNeedRecycle() == RecycledType.YES){
            List<SeqRecycle> recycleList = seqRecycleMapper.selectList(new LambdaQueryWrapper<SeqRecycle>()
                    .eq(SeqRecycle::getId,sequence.getId()).eq(SeqRecycle::getIsRecycled,Boolean.TRUE).orderByAsc(SeqRecycle::getSeqCode));

            if(CollectionUtil.isNotEmpty(recycleList)) {
                SeqRecycle recycle = recycleList.get(0);
                result = recycle.getSeqCode();
                recycle.setIsRecycled(false);
                seqRecycleMapper.updateById(recycle);
            }
        }

        //根据规则生成序列号，并保存回收信息和历史信息，生成的序列号为空时则抛出异常
        if(StrUtil.isBlank(result)){
            Map<Integer,String> items = new HashMap<>();
            synchronized (this) {
                boolean isRepetition = false;
                List<SeqRule> rules = seqRuleMapper.selectList(new LambdaQueryWrapper<>(
                        new SeqRule(sequence.getId())).orderByAsc(SeqRule::getRuleOrder));

                if (CollectionUtil.isEmpty(rules)) {
                    throw new WarningMessageException(String.format("id为[%s]对应的序列未配置对应的生成规则", sequenceName));
                }
                GenerateContext generateContext = new GenerateContext(rules,items,param);
                for (int i = 0; i < rules.size(); i++) {
                    try {
                        SeqRule rule = rules.get(i);
                        generateContext.setSeqRule(rule);
                        String generate = ruleGeneratorFactory.get(rule.getRuleType()).generate(generateContext);
                        items.put(rule.getRuleOrder(),generate);
                    }catch (TriggerOptimisticLockException e){
                        i--;
                    }
                }
            }
            if (MapUtil.isNotEmpty(items)){
                if(StrUtil.isNotBlank(sequence.getDelimiter())){
                    result = CollectionUtil.join(items.values(),sequence.getDelimiter());
                }else{
                    result = CollectionUtil.join(items.values(),StrUtil.EMPTY);
                }
            }else{
                throw new WarningMessageException(String.format("根据名称[%s]生成的序列号为空",sequenceName));
            }

            //保存回收信息
            if(sequence.getNeedRecycle() == RecycledType.YES){
                SeqRecycle recycle = new SeqRecycle(sequence.getId(),result);
                recycle.setIsRecycled(false);
                seqRecycleMapper.insert(recycle);
            }
        }

        //保存序列号占用历史信息
        if(tenantInfo != null){
            SeqHistory history = seqHistoryMapper.selectOne(new QueryWrapper<>(new SeqHistory(sequence.getId(),result)));
            if(history == null) {
                history = new SeqHistory(sequence.getId(), result, tenantInfo.getTenantEntityId(), tenantInfo.getTenantEntityName());
                seqHistoryMapper.insert(history);
            }else{
                history.setTenantEntityId(tenantInfo.getTenantEntityId());
                history.setTenantEntityName(tenantInfo.getTenantEntityName());
                seqHistoryMapper.updateById(history);
            }
        }
        return result;
    }

    @Override
    public void submit(String sequenceName, String sequenceCode, TenantInfo tenantInfo){
        Sequence sequence = getSequenceByName(sequenceName);
        if(StrUtil.isBlank(sequenceCode)){
            throw new WarningMessageException(String.format("待确认的序列[%s]对应的序列值为空",sequenceName));
        }

        if(sequence.getNeedRecycle() == RecycledType.YES){
            SeqRecycle recycle = seqRecycleMapper.selectOne(new QueryWrapper<>(new SeqRecycle(sequence.getId(),sequenceCode)));
            if(recycle == null){
                //TODO 未找到对应的回收信息，进行日志记录
            }else{
                seqRecycleMapper.delete(new QueryWrapper<>(recycle));
            }
        }

        //保存序列号占用历史信息，如果占用者信息为空，则进行日志记录，如果已经有占用历史，则略过
        if(tenantInfo != null && !StrUtil.hasBlank(tenantInfo.getTenantEntityId(),tenantInfo.getTenantEntityName())){
            SeqHistory history = seqHistoryMapper.selectOne(new QueryWrapper<>(new SeqHistory(sequence.getId(),sequenceCode)));
            if(history == null){
                history = new SeqHistory(sequence.getId(),sequenceCode,tenantInfo.getTenantEntityId(),tenantInfo.getTenantEntityName());
                seqHistoryMapper.insert(history);
            }else if(history != null && StrUtil.isBlank(tenantInfo.getTenantEntityId())){
                history.setTenantEntityId(tenantInfo.getTenantEntityId());
                history.setTenantEntityName(tenantInfo.getTenantEntityName());
                seqHistoryMapper.updateById(history);
            }
        }else{
            //TODO 未输入对应的占用者信息，进行日志记录
        }
    }

    @Override
    public void recycle(String sequenceName, String sequenceCode){
        Sequence sequence = getSequenceByName(sequenceName);
        if(StrUtil.isBlank(sequenceCode)){
            throw new WarningMessageException(String.format("序列名称[%s]对应的待回收的序列值不能为空",sequenceName));
        }
        if(sequence.getNeedRecycle() == RecycledType.YES){
            SeqRecycle recycle = seqRecycleMapper.selectOne(new QueryWrapper(new SeqRecycle(sequence.getId(),sequenceCode)));
            if(recycle == null){
                throw new WarningMessageException(String.format("根据序列名称[%s]和序列值[%s]未找到对应的序列回收信息",sequenceName,sequenceCode));
            }else{
                recycle.setIsRecycled(true);
                seqRecycleMapper.updateById(recycle);
            }
        }else{
            throw new WarningMessageException(String.format("根据序列名称[%s]对应的序列配置为不能被回收",sequenceName));
        }
    }

    /**
     * 根据名称查找序列
     */
    private Sequence getSequenceByName(String sequenceName){
        Assert.isTrue(StrUtil.isNotBlank(sequenceName),"序列名称不能为空");

        Sequence sequence = sequenceMapper.selectOne(new LambdaQueryWrapper<Sequence>().eq(Sequence::getSeqName,sequenceName));

        if(sequence == null){
            throw new WarningMessageException(String.format("根据名称[%s]未找到对应的序列号",sequenceName));
        }

        return sequence;
    }

}
