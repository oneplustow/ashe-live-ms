package cn.oneplustow.mc.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.oneplustow.common.constant.Constants;
import cn.oneplustow.config.db.model.TableDataInfo;
import cn.oneplustow.config.db.mybatisplus.core.ServicePlusImpl;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.mc.entity.InteriorMessage;
import cn.oneplustow.mc.entity.criteria.InteriorMessageCriteria;
import cn.oneplustow.mc.entity.criteria.InteriorMessageListCriteria;
import cn.oneplustow.mc.entity.dto.InteriorMessageSaveDto;
import cn.oneplustow.mc.entity.vo.InteriorMessageVo;
import cn.oneplustow.mc.mapper.InteriorMessageMapper;
import cn.oneplustow.mc.service.IInteriorMessageService;
import cn.opl.generate.QueryUtil;
import cn.opl.mapstruct.MapStructContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 站内信Service业务层处理
 *
 * @author cc
 * @date 2021-10-02
 */
@Service
public class InteriorMessageServiceImpl extends ServicePlusImpl<InteriorMessageMapper, InteriorMessage> implements IInteriorMessageService {

	@Autowired
	private MapStructContext mapStructContext;

	@Override
	public InteriorMessage saveMessage(InteriorMessageSaveDto saveDto){
		InteriorMessage interiorMessage = mapStructContext.conver(saveDto, InteriorMessage.class);
		this.save(interiorMessage);
		return interiorMessage;
	}

	@Override
	public void read(Long userId, List<Integer> id) {
		this.update(new LambdaUpdateWrapper<InteriorMessage>()
			.eq(InteriorMessage::getToUser,userId)
			.in(InteriorMessage::getId,id)
			.set(InteriorMessage::getStatus, Constants.InteriorMessageStatus.已读));
	}

	@Override
	public TableDataInfo<InteriorMessageVo> list(InteriorMessageCriteria criteria) {
		QueryWrapper<InteriorMessage> generate = QueryUtil.generate(criteria);
        Page page = this.page(criteria.build(), QueryUtil.generate(criteria));
        page.setRecords(mapStructContext.conver(page.getRecords(),InteriorMessageVo.class));
        return TableDataInfo.build(page);

	}

	@Override
    public InteriorMessageVo queryById(Long id){
        return mapStructContext.conver(getById(id), InteriorMessageVo.class);
    }

    @Override
    public TableDataInfo<InteriorMessageVo> queryPageList(InteriorMessageListCriteria bo) {
        Page page = this.page(bo.build(), QueryUtil.generate(bo));
		Page<InteriorMessageVo> pageVo = this.pageVo(bo.build(), QueryUtil.generate(bo), InteriorMessageVo.class);
        return TableDataInfo.build(pageVo);
    }

    @Override
    public List<InteriorMessageVo> queryList(InteriorMessageListCriteria bo) {
        return listVo(QueryUtil.generate(bo),InteriorMessageVo.class);
    }


}
