package cn.oneplustow.lc.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.lc.entity.PlayRecord;
import cn.oneplustow.lc.mapper.PlayRecordMapper;
import cn.oneplustow.lc.service.IPlayRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播记录Service业务层处理
 * 
 * @author cc
 * @date 2021-04-14
 */
@Service
public class PlayRecordServiceImpl extends ServiceImpl<PlayRecordMapper, PlayRecord> implements IPlayRecordService
{

    @Override
    public List<PlayRecord> page(PlayRecord playRecord) {
        PageUtil.startPage();
        LambdaQueryWrapper<PlayRecord> queryWrapper = new LambdaQueryWrapper<PlayRecord>();
        if (playRecord.getStartDateTime() != null) {
            queryWrapper.eq(PlayRecord::getStartDateTime, playRecord.getStartDateTime());
        }
        if (playRecord.getEndDateTime() != null) {
            queryWrapper.eq(PlayRecord::getEndDateTime, playRecord.getEndDateTime());
        }
        if (StrUtil.isNotBlank(playRecord.getStatus())) {
            queryWrapper.eq(PlayRecord::getStatus, playRecord.getStatus());
        }
        List<PlayRecord> list = this.list(queryWrapper);
        return list;
    }
}
