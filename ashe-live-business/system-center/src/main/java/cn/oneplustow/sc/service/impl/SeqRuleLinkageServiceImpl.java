package cn.oneplustow.sc.service.impl;


import cn.oneplustow.sc.entity.SeqRuleLinkage;
import cn.oneplustow.sc.mapper.SeqRuleLinkageMapper;
import cn.oneplustow.sc.service.ISeqRuleLinkageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 编码规则 服务接口类
 * </p>
 *
 * @author greatmap
 * @since 2017-08-10
 */
@Service("seqRuleLinkageService")
public class SeqRuleLinkageServiceImpl extends ServiceImpl<SeqRuleLinkageMapper, SeqRuleLinkage> implements ISeqRuleLinkageService {


    @Override
    public boolean insertOrUpdate(SeqRuleLinkage seqRuleLinkage) {
        return saveOrUpdate(seqRuleLinkage);
    }

    @Override
    public SeqRuleLinkage getLinkage(SeqRuleLinkage seqRuleLinkage){
        return getOne(new LambdaQueryWrapper<>(seqRuleLinkage));
    }

}
