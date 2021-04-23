package cn.oneplustow.common.mapstruct;

import java.util.List;

/**
 * @author cc
 */
public interface IMapStruct<S,T> {

    /**
     * 将对象 S（From） 转换到对象 T（To）
     * @param s
     * @return
     */
    T convers(S s);

    /**
     * 将对象 T（To） 转换到对象 S（From）
     * @param t
     * @return
     */
    S convert(T t);

    /**
     * 将对象 tList（To） 转换到对象 sList（From）
     * @param tList
     * @return
     */
    List<S> convert(List<T> tList);

    /**
     * 将对象 sList（From）转换到对象 tList（To）
     * @param sList
     * @return
     */
    List<T> convers(List<S> sList);
}
