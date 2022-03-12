package cn.oneplustow.sc.service.impl;


import cn.oneplustow.sc.entity.SeqHistory;
import cn.oneplustow.sc.mapper.SeqHistoryMapper;
import cn.oneplustow.sc.service.ISeqHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 编码历史表 服务接口类
 * </p>
 *
 * @author greatmap
 * @since 2017-08-10
 */
@Service("seqHistoryService")
public class SeqHistoryServiceImpl extends ServiceImpl<SeqHistoryMapper, SeqHistory> implements ISeqHistoryService {

}
