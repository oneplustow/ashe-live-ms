package cn.oneplustow.api.user.async;

import cn.hutool.extra.spring.SpringUtil;
import cn.oneplustow.api.user.model.SysOperLogModel;
import cn.oneplustow.api.user.service.OperLogService;
import cn.oneplustow.common.web.util.AddressUtils;

import java.util.TimerTask;

/**
 * 操作日志记录
 *
 * @param operLog 操作日志信息
 * @return 任务task
 */
/**
 * 操作日志记录
 * @author cc
 * @date 2020/11/27 16:07
 */
public class RecordOperTimerTask extends TimerTask {
    private SysOperLogModel operLog;

    /**
     * 操作日志记录
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public RecordOperTimerTask(SysOperLogModel operLog) {
        this.operLog = operLog;
    }

    @Override
    public void run() {
        // 远程查询操作地点
        operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
        SpringUtil.getBean(OperLogService.class).insertOperlog(operLog);
    }
}
