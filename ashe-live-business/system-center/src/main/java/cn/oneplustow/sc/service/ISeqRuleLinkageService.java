package cn.oneplustow.sc.service;


import cn.oneplustow.sc.entity.SeqRuleLinkage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 编码规则  服务接口类
 * </p>
 *
 * @author greatmap
 * @since 2017-08-10
 */
public interface ISeqRuleLinkageService extends IService<SeqRuleLinkage> {

    /**
     * 插入或更新序列号规则
     * @param seqRule 序列号规则
     * @return 
     */
    boolean insertOrUpdate(SeqRuleLinkage seqRuleLinkage);

    SeqRuleLinkage getLinkage(SeqRuleLinkage seqRuleLinkage);
}