package cn.oneplustow.common.mapstruct;

import cn.hutool.core.lang.Assert;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实体类转换器上下文
 * 对所有实体类转换器 StructMapper 进行汇总解析
 * 对外提供统一方法进行调用
 * @author cc
 */
//@Component
public class MapStructContext {

    private List<IMapStruct> iMapStructs;

    @Autowired(required = false)
    public void setiMapStructs(List<IMapStruct> iMapStructs) {
        this.iMapStructs = iMapStructs;
    }

    /**实体映射转换器缓存
     * key为 fromClass +:+ toClass
     * 例如：com.String:com.Integer
     * value为 StructMapperParse解析对象
     * */
    private ConcurrentHashMap<String, MapStructParse> map = new ConcurrentHashMap<>(48);

    public <T> List<T>conver(List<?> from,Class<T> to){
        if(CollectionUtils.isEmpty(from)){return new ArrayList<T>();}
        Boolean forward = isForward(from.get(0).getClass(), to);
        if(forward == null){return null;}
        IMapStruct iMapStruct = getIMapStruct(from.get(0).getClass(), to);
        if (iMapStruct == null) {return null;}
        return forward ? iMapStruct.convers(from) : iMapStruct.convert(from);
    }

    public <T> T conver(Object from,Class<T> to){
        if(from == null){return null;}
        Boolean forward = isForward(from.getClass(), to);
        Assert.notNull(forward,"无法找到具体的转换规则");
        IMapStruct iMapStruct = getIMapStruct(from.getClass(), to);
        Assert.notNull(iMapStruct,"无法找到具体的转换规则");
        return forward ? (T)iMapStruct.convers(from) : (T)iMapStruct.convert(from);
    }

    /**
     *
     * 通过当前class和 转换到的class  来获取是正向查询还是反向查询
     * 正向查询则表示 和 接口泛型一致的转换换 A.class  -> B.class
     * 反向查询则表 和接口泛型相反的转换 B.class  -> A.class
     * 为空则表示 没有转换实体
     * @param from
     * @param to
     * @return
     */
    public Boolean isForward(Class from,Class to){
        String fromTypeName = from.getTypeName();
        String toTypeName = to.getTypeName();
        //正向查询
        String key = fromTypeName + ":" + toTypeName;
        if (map.containsKey(key)) {
            return true;
        }
        //反向查询
        key = toTypeName +":"+ fromTypeName;
        if (map.containsKey(key)) {
            return false;
        }
        return null;
    }

    /**
     * 通过当前class和 转换到的class  来获取entityMapper 实体转换映射器
     * 获取有两种情况，一种是正向查询，一种是反向查询
     * 正向查询则表示 和 接口泛型一致的转换换 A.class  -> B.class
     * 反向查询则表示 和接口泛型相反的转换 B.class  -> A.class
     * @param from
     * @param to
     * @return
     */
    public IMapStruct getIMapStruct(Class from, Class to){
        String fromTypeName = from.getTypeName();
        String toTypeName = to.getTypeName();
        //正向查询
        String key = fromTypeName + ":" + toTypeName;
        if (map.containsKey(key)) {
            return map.get(key).getIMapStruct();
        }
        //反向查询
        key = toTypeName +":"+ fromTypeName;
        if (map.containsKey(key)) {
            return map.get(key).getIMapStruct();
        }
        return null;
    }

    /**
     * 在初始化完成后执行
     * 对ico里面所有的entityMapper进解析，
     * 将解析后的数据保存到map映射表
     * 来表明 A.class ->  B.class
     */
    @PostConstruct
    public void initContext(){
        if (CollectionUtils.isEmpty(iMapStructs)) {
            return;
        }
         for (IMapStruct iMapStruct : iMapStructs) {
            MapStructParse structMapperParse = parseIMapStruct(iMapStruct);
            if(structMapperParse == null){continue;}
            map.put(structMapperParse.getKey(),structMapperParse);
        }
    }

    /**
     * 解析entityClass类，获取接口的泛型类型并封装为StructMapperParse对象
     * @param iMapStruct
     * @return
     */
    private MapStructParse parseIMapStruct(IMapStruct iMapStruct) {
        Class entityMapperClass = IMapStruct.class;
        //获取EntityMapper的所有接口
        Class<?>[] interfaces = iMapStruct.getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            if (!entityMapperClass.isAssignableFrom(anInterface)) {
                continue;
            }
            //获取继承了EntityMapper接口的 接口
            Type[] genericInterfaces = anInterface.getGenericInterfaces();
            if (ArrayUtils.isEmpty(genericInterfaces)) {
                continue;
            }
            //获取接口的泛型类型
            ParameterizedTypeImpl genericInterface = (ParameterizedTypeImpl) genericInterfaces[0];
            Type[] actualTypeArguments = genericInterface.getActualTypeArguments();
            Type fromClass = actualTypeArguments[0];
            Type toClass = actualTypeArguments[1];
            //返回StrutsMapper解析对象
            return new MapStructParse(fromClass,toClass,iMapStruct);
        }
        return null;
    }

    @Data
    class MapStructParse {
        private String from;
        private String to;
        private Type fromType;
        private Type toType;
        private IMapStruct iMapStruct;

        public String getKey(){
            return from+":"+to;
        }

        public MapStructParse(Type fromType, Type toType, IMapStruct iMapStruct) {
            this.from = fromType.getTypeName();
            this.to = toType.getTypeName();
            this.fromType = fromType;
            this.toType = toType;
            this.iMapStruct = iMapStruct;
        }

    }

}
