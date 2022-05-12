package cn.oneplustow.config.db.mybatisplus.core;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.opl.mapstruct.MapStructContext;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * IServicePlus 实现类
 *
 * @author Lion Li
 */
@Slf4j
@SuppressWarnings("unchecked")
public class ServicePlusImpl<M extends BaseMapperPlus<T>, T> extends ServiceImpl<M, T> implements IServicePlus<T> {

	@Autowired
	protected M baseMapper;
	@Autowired
	private MapStructContext mapStructContext;

	@Override
	public M getBaseMapper() {
		return baseMapper;
	}


	protected Class<T> entityClass = currentModelClass();

	@Override
	public Class<T> getEntityClass() {
		return entityClass;
	}

	protected Class<T> mapperClass = currentMapperClass();

	@Override
	protected Class<T> currentMapperClass() {
		return (Class<T>) this.getResolvableType().as(ServicePlusImpl.class).getGeneric(0).getType();
	}

	@Override
	protected Class<T> currentModelClass() {
		return (Class<T>) this.getResolvableType().as(ServicePlusImpl.class).getGeneric(1).getType();
	}


	@Override
	protected ResolvableType getResolvableType() {
		return ResolvableType.forClass(ClassUtils.getUserClass(getClass()));
	}

	/**
	 * 单条执行性能差 适用于列表对象内容不确定
	 */
	@Override
	public boolean saveBatch(Collection<T> entityList, int batchSize) {
		return super.saveBatch(entityList, batchSize);
	}

	@Override
	public boolean saveOrUpdate(T entity) {
		return super.saveOrUpdate(entity);
	}

	/**
	 * 单条执行性能差 适用于列表对象内容不确定
	 */
	@Override
	public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
		return super.saveOrUpdateBatch(entityList, batchSize);
	}

	@Override
	public boolean updateBatchById(Collection<T> entityList, int batchSize) {
		return super.updateBatchById(entityList, batchSize);
	}

	/**
	 * 单sql批量插入( 全量填充 无视数据库默认值 )
	 * 适用于无脑插入
	 */
	@Override
	public boolean saveBatch(Collection<T> entityList) {
		return saveBatch(entityList, DEFAULT_BATCH_SIZE);
	}

	@Override
	public boolean saveOrUpdateBatch(Collection<T> entityList) {
		return saveOrUpdateBatch(entityList, DEFAULT_BATCH_SIZE);
	}

	@Override
	public boolean updateBatchById(Collection<T> entityList) {
		return updateBatchById(entityList, DEFAULT_BATCH_SIZE);
	}

	/**
	 * 单sql批量插入( 全量填充 无视数据库默认值 )
	 * 适用于无脑插入
	 */
	@Override
	public boolean saveAll(Collection<T> entityList) {
		return baseMapper.insertAll(entityList) == entityList.size();
	}

	/**
	 * 根据 ID 查询
	 *
	 * @param id 主键ID
	 */
	@Override
	public <R> R getVoById(Serializable id, Class<R> voClass) {
		T t = getBaseMapper().selectById(id);
		return mapStructContext.conver(t, voClass);

	}

	/**
	 * 查询（根据ID 批量查询）
	 *
	 * @param idList 主键ID列表
	 */
	@Override
	public <R> List<R> listVoByIds(Collection<? extends Serializable> idList, Class<R> voClass) {
		List<T> list = getBaseMapper().selectBatchIds(idList);
		return mapStructContext.conver(list, voClass);

	}

	/**
	 * 查询（根据 columnMap 条件）
	 *
	 * @param columnMap 表字段 map 对象
	 */
	@Override
	public <R> List<R> listVoByMap(Map<String, Object> columnMap, Class<R> voClass) {
		List<T> list = getBaseMapper().selectByMap(columnMap);
		return mapStructContext.conver(list, voClass);
	}

	/**
	 * 根据 Wrapper，查询一条记录 <br/>
	 * <p>结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")</p>
	 *
	 * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 */
	@Override
	public <R> R getVoOne(Wrapper<T> queryWrapper, Class<R> voClass) {
		T t = getOne(queryWrapper, true);
		return mapStructContext.conver(t, voClass);
	}

	/**
	 * 查询列表
	 *
	 * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 */
	@Override
	public <R> List<R> listVo(Wrapper<T> queryWrapper, Class<R> voClass) {
		List<T> list = getBaseMapper().selectList(queryWrapper);
		return mapStructContext.conver(list, voClass);
	}

	/**
	 * 翻页查询
	 *
	 * @param page         翻页对象
	 * @param queryWrapper 实体对象封装操作类
	 */
	@Override
	public <R,E,P extends IPage<T>> E pageVo(P page, Wrapper<T> queryWrapper, Class<R> voClass) {

		IPage<T> result = getBaseMapper().selectPage(page, queryWrapper);
		List<R> conver = mapStructContext.conver(result.getRecords(), voClass);
		IPage<R> iPage = BeanUtil.toBean(result, result.getClass(),CopyOptions.create().setIgnoreProperties("records"));
		iPage.setRecords(conver);
		return (E) iPage;
	}

}
