package cn.oneplustow.sc.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.sc.entity.SeqRule;
import cn.oneplustow.sc.entity.Sequence;
import cn.oneplustow.sc.mapper.SeqRuleMapper;
import cn.oneplustow.sc.mapper.SequenceMapper;
import cn.oneplustow.sc.service.ISequenceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业务编码 服务接口类
 * </p>
 *
 * @author greatmap
 * @since 2017-08-10
 */
@Service("sequenceService")
public class SequenceServiceImpl extends ServiceImpl<SequenceMapper, Sequence> implements ISequenceService {

    @Autowired
    private SequenceMapper sequenceMapper;
    
    @Autowired
    private SeqRuleMapper seqRuleMappere;

    @Override
    public Sequence insertOrUpdateSeq(Sequence sequence) {
    	if (sequence ==null || StrUtil.isBlank(sequence.getSeqName())) {
			return null;
		}
    	if (StrUtil.isNotBlank(sequence.getId())) {
			Sequence sequence2 = sequenceMapper.selectById(sequence.getId());
			if (sequence2 != null) {
				List<Sequence> selectList = sequenceMapper.selectList(new LambdaQueryWrapper<Sequence>()
                        .eq(Sequence::getSeqName, sequence.getSeqName()).ne(Sequence::getId, sequence.getId()));
				if (CollectionUtil.isNotEmpty(selectList)) {
					 throw new WarningMessageException("序列号名称已存在!");
				}
				sequenceMapper.updateById(sequence);
				return sequence;
			}
			return null;
		}else {
			List<Sequence> selectList = sequenceMapper.selectList(new LambdaQueryWrapper<Sequence>().eq(Sequence::getSeqName, sequence.getSeqName()));
			if (CollectionUtil.isNotEmpty(selectList)) {
				 throw new WarningMessageException("序列号名称已存在!");
			}
			sequence.setId(IdUtil.simpleUUID());
			sequenceMapper.insert(sequence);
			return sequence;
		}
    }

    @Override
    public boolean delSequence(List<String> ids) {
    	ids.stream().forEach(m->{
    		 List<SeqRule> seqRuleBySeqId = getSeqRuleBySeqId(m);
    		 if (CollectionUtil.isNotEmpty(seqRuleBySeqId)) {
				seqRuleBySeqId.stream().forEach(n->seqRuleMappere.deleteById(n.getId()));
			}
    		 
    	});
    	sequenceMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public IPage<Sequence> getPage(Page<Sequence> page, String seqName) {
        LambdaQueryWrapper<Sequence> wrapper = new LambdaQueryWrapper<Sequence>();
        if (StrUtil.isNotBlank(seqName)) {
            wrapper.like(Sequence::getSeqName, seqName);
        }
        IPage<Sequence> sequenceIPage = sequenceMapper.selectPage(page, wrapper);
        return sequenceIPage;
    }

    @Override
    public List<Sequence> getList() {
        return getList(true);
    }

    @Override
    public List<Sequence> getList(Boolean includeRule) {
        QueryWrapper<Sequence> w = new QueryWrapper<>();
        List<Sequence> list = sequenceMapper.selectList(w);
        if (CollectionUtil.isEmpty(list)) {
			return null;
		}
		if(includeRule) {
            list.stream().forEach(m -> {
                List<SeqRule> seqRules = getSeqRuleBySeqId(m.getId());
                if (CollectionUtil.isNotEmpty(seqRules)) {
                    m.setSeqRules(seqRules);
                }
            });
        }
        return list;
    }

	@Override
	public List<SeqRule> getSeqRuleBySeqId(String seqId) {
        LambdaQueryWrapper<SeqRule> wrapper = new LambdaQueryWrapper<SeqRule>();
        wrapper.eq(SeqRule::getSeqId, seqId);
		return seqRuleMappere.selectList(wrapper);
	}
}
