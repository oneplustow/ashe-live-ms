package cn.oneplustow.sc.service;


import cn.oneplustow.sc.entity.SeqRule;
import cn.oneplustow.sc.entity.Sequence;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 业务编码  服务接口类
 * </p>
 *
 * @author greatmap
 * @since 2017-08-10
 */
public interface ISequenceService extends IService<Sequence> {

    /**
     * 更新或插入编码
     * @param sequence 业务编码
     * @return 业务编码
     */
    Sequence insertOrUpdateSeq(Sequence sequence);

    /**
     * 批量删除业务编码
     * @param ids  编码批ID
     * @return 是否删除成功
     */
    boolean delSequence(List<String> ids);

    /**
     * 根据编码名称获取分页信息
     * @param page 分页信息
     * @param seqName 编码名称
     * @return 编码分页信息
     */
    IPage<Sequence> getPage(Page<Sequence> page, String seqName);

    /**
     * 获取所有编码信息,包含编码规则
     * @return 编码信息list集合
     */
    List<Sequence> getList();

    /**
     * 获取所有编码信息，根据includeRule参数，决定是否填充编码规则
     * @param includeRule 是否包含编码规则
     * @return 编码信息list集合
     */
    List<Sequence> getList(Boolean includeRule);
    
    /**
     * 通过id查询业务编码信息
     * @param seqId  编码id
     * @return 查询的编码信息
     */ 
    List<SeqRule> getSeqRuleBySeqId(String seqId);

}