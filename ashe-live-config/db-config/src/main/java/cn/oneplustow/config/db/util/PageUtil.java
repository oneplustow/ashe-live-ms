package cn.oneplustow.config.db.util;

import cn.oneplustow.common.web.page.PageDomain;
import cn.oneplustow.common.web.page.TableSupport;
import cn.oneplustow.config.db.model.TableDataInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author cc
 * @date 2020/12/2 14:25
 */
public class PageUtil {

    public static Page startPage(PageDomain pageDomain) {
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        return PageHelper.startPage(pageNum, pageSize, orderBy);
    }

    /**
     * 设置请求分页数据
     */
    public static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        startPage(pageDomain);
    }


    /**
     * 响应请求分页数据
     */
    public static TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
}
