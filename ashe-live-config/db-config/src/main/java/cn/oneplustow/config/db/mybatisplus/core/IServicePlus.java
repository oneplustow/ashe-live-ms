package cn.oneplustow.config.db.mybatisplus.core;

import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 自定义 Service 接口, 实现 数据库实体与 vo 对象转换返回
 *
 * @author cc
 * @since 2021-05-13
 */
public interface IServicePlus<T> extends IService<T> {

	/**
	 * @param id          主键id
	 */
	<R> R getVoById(Serializable id, Class<R> voClass);


	/**
	 * @param idList      id列表
	 * @param copyOptions copy条件
	 * @return K对象
	 */
	<R> List<R> listVoByIds(Collection<? extends Serializable> idList, Class<R> voClass);

	/**
	 * @param columnMap   表字段 map 对象
	 * @param copyOptions copy条件
	 * @return K对象
	 */
	<R> List<R> listVoByMap(Map<String, Object> columnMap, Class<R> voClass);


	/**
	 * @param queryWrapper 查询条件
	 * @param copyOptions  copy条件
	 * @return K对象
	 */
	<R> R getVoOne(Wrapper<T> queryWrapper, Class<R> voClass);


	/**
	 * @param queryWrapper 查询条件
	 * @param copyOptions  copy条件
	 * @return K对象
	 */
	<R> List<R> listVo(Wrapper<T> queryWrapper, Class<R> voClass);


	default <R> List<R> listVo(Class<R> voClass) {
		return listVo(Wrappers.emptyWrapper(),voClass);
	}


	/**
	 * @param page         分页对象
	 * @param queryWrapper 查询条件
	 * @param copyOptions  copy条件
	 * @return K对象
	 */
	<R,E,P extends IPage<T>> E pageVo(P page, Wrapper<T> queryWrapper, Class<R> voClass);



	default <R,E,P extends IPage<T>> E pageVo(P page,Class<R> voClass) {
		return pageVo(page, Wrappers.emptyWrapper(),voClass);
	}

	boolean saveAll(Collection<T> entityList);
}

