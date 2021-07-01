package cn.oneplustow.common.mapstruct;

import cn.opl.mapstruct.IMapStructAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author CC
 * @title: MyMapStruct
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/6/1522:03
 */
@Component
public class MyMapStructAdapter implements IMapStructAdapter<IMapStruct> {
    @Override
    public Object converFrom(IMapStruct iMapStruct, Object o) {
        return iMapStruct.convers(o);
    }

    @Override
    public Object converTo(IMapStruct iMapStruct, Object o) {
        return iMapStruct.convert(o);
    }

    @Override
    public List converTo(IMapStruct iMapStruct, List list) {
        return iMapStruct.convert(list);

    }

    @Override
    public List converFrom(IMapStruct iMapStruct, List list) {
        return iMapStruct.convers(list);

    }
}
