package cn.oneplustow.api.sc.util;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.oneplustow.api.sc.model.DictDataResp;
import cn.oneplustow.api.sc.service.DictDataFeginApi;
import cn.oneplustow.common.annoatation.DictValue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cc
 * @date 2020/11/27 15:38
 */
public class DictDataUtil {

    public static void convDictValue(Object object){
        List list = new ArrayList();
        list.add(object);
        convDictValue(list);
    }

    public static void convDictValue(List objectList){
        if (CollUtil.isEmpty(objectList)) { return ;}
        Object one = objectList.get(0);
        Map<String, List<Field>> fieldMap = getFieldMap(one);
        DictDataFeginApi dictDataService = SpringUtil.getBean(DictDataFeginApi.class);
        if(ObjectUtil.isNull(dictDataService)){return;}
        for (Map.Entry<String, List<Field>> stringListEntry : fieldMap.entrySet()) {
            List<DictDataResp> sysDictData = dictDataService.selectDictDataByType(stringListEntry.getKey());
            Map<String,String> sysDictDataMap = sysDictData.stream().collect(Collectors.toMap(DictDataResp::getDictValue, DictDataResp::getDictLabel));
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

}
