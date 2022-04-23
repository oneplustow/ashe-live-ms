package cn.oneplustow.config.db.util;

import cn.oneplustow.common.web.page.PageDomain;
import cn.oneplustow.common.web.util.ServletUtils;
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
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

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
        PageDomain pageDomain = getPageDomain();
        startPage(pageDomain);
    }

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        Integer pageNum = ServletUtils.getParameterToInt(PAGE_NUM);
        Integer pageSize = ServletUtils.getParameterToInt(PAGE_SIZE);
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        pageDomain.setPageNum(pageNum);
        pageDomain.setPageSize(pageSize);
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));
        return pageDomain;
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
