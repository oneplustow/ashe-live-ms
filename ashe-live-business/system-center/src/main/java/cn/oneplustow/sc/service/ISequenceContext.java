package cn.oneplustow.sc.service;


import cn.oneplustow.sc.context.TenantInfo;

import java.util.Map;

/**
 * 序列号接口
 * @author cc
 *
 */
public interface ISequenceContext {
    /**
     * 根据序列名称生成序列值
     * 序列名称为空或未找到对应的序列值时抛出异常
     * 1、如果当前序列配置为可回收：
     *      1)如果且存在待回收的，则从回收信息中取序列值；
     *      2)如果不存在可回收利用的序列值时根据规则生成，并记录回收信息
     * 2、如果占用者信息tenantInfo存在时进行占用者信息记录，不存在时不记录
     *
     * @param sequenceName 序列号名称
     * @param tenantInfo 占用者信息
     * @return
     */
    String apply(String sequenceName, TenantInfo tenantInfo);

    String apply(String sequenceName, Map<String, Object> param, TenantInfo tenantInfo);

    /**
     * 确认序列值使用
     * 序列名称为空或根据序列名称未找到对应的序列值时抛出异常
     * 序列值为空时抛出异常
     * 1、如果序列值是可回收的，确认后将删除回收信息，表明这条序列号已经使用了是无法再回收的
     * 2、如果占用者信息tenantInfo存在时，进行占用者信息记录，不存在时不记录，
     *      通常调用时都要提供占用者信息，不提供时程序进行日志记录
     * @param sequenceName 序列号名称
     * @param sequenceCode 序列值
     * @param tenantInfo 占用者信息
     */
    void submit(String sequenceName, String sequenceCode, TenantInfo tenantInfo);

    /**
     * 回收指定的序列值
     * 序列名称为空或根据序列名称未找到对应的序列值时抛出异常
     * 1、如果对应的序列是可回收的，则查找对应的回收信息
     *      1）找到回收信息，将其从持久化中删除
     *      2）未找到，抛出异常
     * 2、如果对应的序列是不可回收的，则抛出异常
     * @param sequenceName 序列号名称
     * @param sequenceCode 序列号值
     */
   void recycle(String sequenceName, String sequenceCode);
}
