package cn.oneplustow.mc.service;


import cn.oneplustow.config.db.model.TableDataInfo;
import cn.oneplustow.config.db.mybatisplus.core.IServicePlus;
import cn.oneplustow.mc.entity.InteriorMessage;
import cn.oneplustow.mc.entity.criteria.InteriorMessageCriteria;
import cn.oneplustow.mc.entity.criteria.InteriorMessageListCriteria;
import cn.oneplustow.mc.entity.dto.InteriorMessageSaveDto;
import cn.oneplustow.mc.entity.vo.InteriorMessageVo;

import java.util.Collection;
import java.util.List;

/**
 * 站内信Service接口
 *
 * @author cc
 * @date 2021-10-02
 */
public interface IInteriorMessageService extends IServicePlus<InteriorMessage> {
	/**
	 * 查询单个
	 * @return
	 */
	InteriorMessageVo queryById(Long id);

	/**
	 * 查询列表
	 */
    TableDataInfo<InteriorMessageVo> queryPageList(InteriorMessageListCriteria criteria);

	/**
	 * 查询列表
	 */
	List<InteriorMessageVo> queryList(InteriorMessageListCriteria criteria);



	/**
	 * 获取分页列表
	 * @param criteria
	 * @return
	 */
	TableDataInfo<InteriorMessageVo> list(InteriorMessageCriteria criteria);

	InteriorMessage saveMessage(InteriorMessageSaveDto saveDto);

	/**
	 * 已读
	 * @param userId
	 * @param id
	 */
	void read(Long userId, List<Integer> id);
}
