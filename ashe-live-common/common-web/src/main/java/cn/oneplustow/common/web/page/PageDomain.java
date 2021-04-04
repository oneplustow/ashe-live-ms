package cn.oneplustow.common.web.page;


import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

/**
 * 分页数据
 * 
 * @author ruoyi
 */
@ApiModel("分页实体对象")
public class PageDomain
{
    /** 当前记录起始索引 */
    @ApiModelProperty("当前页")
    private Integer pageNum;
    /** 每页显示记录数 */
    @ApiModelProperty("每页记录数")
    private Integer pageSize;
    /** 排序列 */
    @ApiModelProperty("排序字段")
    private String orderByColumn;
    /** 排序的方向 "desc" 或者 "asc". */
    @ApiModelProperty("排序的方向")
    private String isAsc;

    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        return StrUtil.toCamelCase(orderByColumn) + " " + isAsc;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn)
    {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String isAsc)
    {
        this.isAsc = isAsc;
    }
}
