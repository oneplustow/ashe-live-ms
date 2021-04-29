package cn.oneplustow.sc.service.impl;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.oneplustow.common.annoatation.DictValue;
import cn.oneplustow.sc.entity.SysDictData;
import cn.oneplustow.sc.mapper.SysDictDataMapper;
import cn.oneplustow.sc.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService
{
    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Override
    public void convDictValue(Object object){
        List list = new ArrayList();
        list.add(object);
        convDictValue(list);
    }

    @Override
    public void convDictValue(List objectList){
        if (CollUtil.isEmpty(objectList)) { return ;}
        Object one = objectList.get(0);
        Map<String, List<Field>> fieldMap = getFieldMap(one);
        for (Map.Entry<String, List<Field>> stringListEntry : fieldMap.entrySet()) {
            List<SysDictData> sysDictData = selectDictDataByType(stringListEntry.getKey());
            Map<String,String> sysDictDataMap = sysDictData.stream().collect(Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel));
            List<Field> fields = stringListEntry.getValue();
            for (Field field : fields) {
                for (Object object : objectList) {
                    Object fieldValue = ReflectUtil.getFieldValue(object, field);
                    if(fieldValue == null){continue;}
                    if (fieldValue instanceof String) {
                        String fieldStr = (String)fieldValue;
                        String[] split = fieldStr.split(",");
                        List<String> labelList = new ArrayList<>();
                        for (String value : split) {
                            String label = sysDictDataMap.get(fieldValue);
                            labelList.add(label);
                        }
                        ReflectUtil.setFieldValue(object,field,String.join(",",labelList));
                    }

                }
            }
        }
    }

    private static Map<String, List<Field>> getFieldMap(Object t) {
        Map<String, List<Field>> typeFieldMap = new HashMap<>(16);
        Field[] fields = ReflectUtil.getFields(t.getClass());
        for (Field field : fields) {
            DictValue annotation = AnnotationUtil.getAnnotation(field, DictValue.class);
            if (annotation != null){
                String value = annotation.value();
                List<Field> fields1 = typeFieldMap.get(value);
                if(fields1 == null){
                    typeFieldMap.put(value, CollUtil.newArrayList(field));
                }else{
                    fields1.add(field);
                }
            }
        }
        return typeFieldMap;
    }

    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData)
    {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue)
    {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode)
    {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 通过字典ID删除字典数据信息
     * 
     * @param dictCode 字典数据ID
     * @return 结果
     */
    @Override
    public int deleteDictDataById(Long dictCode)
    {
        return dictDataMapper.deleteDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据信息
     * 
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */
    public int deleteDictDataByIds(Long[] dictCodes)
    {
        return dictDataMapper.deleteDictDataByIds(dictCodes);
    }

    /**
     * 新增保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData dictData)
    {
        return dictDataMapper.insertDictData(dictData);
    }

    /**
     * 修改保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData dictData)
    {
        return dictDataMapper.updateDictData(dictData);
    }
}
