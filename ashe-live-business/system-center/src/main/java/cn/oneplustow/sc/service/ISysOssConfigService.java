package cn.oneplustow.sc.service;


import cn.oneplustow.config.db.model.TableDataInfo;
import cn.oneplustow.config.db.mybatisplus.core.IServicePlus;
import cn.oneplustow.sc.entity.SysOssConfig;
import cn.oneplustow.sc.entity.bo.SysOssConfigBo;
import cn.oneplustow.sc.entity.vo.SysOssConfigVo;

import java.util.Collection;
import java.util.List;

/**
 * 云存储配置Service接口
 *
 */
public interface ISysOssConfigService extends IServicePlus<SysOssConfig> {

	List<SysOssConfigVo> getVoByConfigKey(String configKey);

	/**
	 * 查询单个
	 */
	SysOssConfigVo queryById(Integer ossConfigId);

	/**
	 * 查询列表
	 */
    TableDataInfo<SysOssConfigVo> queryPageList(SysOssConfigBo bo);


	/**
	 * 根据新增业务对象插入云存储配置
	 * @param bo 云存储配置新增业务对象
	 * @return
	 */
	Boolean insertByBo(SysOssConfigBo bo);

	/**
	 * 根据编辑业务对象修改云存储配置
	 * @param bo 云存储配置编辑业务对象
	 * @return
	 */
	Boolean updateByBo(SysOssConfigBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

	/**
	 * 启用停用状态
	 */
	int updateOssConfigStatus(SysOssConfigBo bo);

}
