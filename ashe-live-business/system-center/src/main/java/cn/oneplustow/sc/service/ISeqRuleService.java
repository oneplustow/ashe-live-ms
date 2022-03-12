package cn.oneplustow.sc.service;


import cn.oneplustow.sc.entity.SeqRule;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 编码规则  服务接口类
 * </p>
 *
 * @author cc
 * @since 2022-03-10
 */
public interface ISeqRuleService extends IService<SeqRule> {

    /**
     * 插入或更新序列号规则
     * @param seqRule 序列号规则
     * @return 
     */
    SeqRule insertOrUpdateRule(SeqRule seqRule);

    /**
     * 批量删除序列号
     * @param ids 批id
     * @return 是否删除成功
     */
    boolean delSeqRule(List<String> ids);

    /**
     * 根据id获得序列号分页信息
     * @param page 分页信息
     * @param seqId 序列号id
     * @return 序列号的分页信息
     */
    IPage<SeqRule> getPage(Page<SeqRule> page, String seqId);

    /**
     * 查找枚举信息
     * @return 枚举JSON信息
     */
    JSONObject selectEnums();
}