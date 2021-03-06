package cn.oneplustow.lc.service;


import cn.oneplustow.lc.entity.PlayRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 直播记录Service接口
 * 
 * @author cc
 * @date 2021-04-14
 */
public interface IPlayRecordService extends IService<PlayRecord>
{

    List<PlayRecord> page(PlayRecord playRecord);
}
