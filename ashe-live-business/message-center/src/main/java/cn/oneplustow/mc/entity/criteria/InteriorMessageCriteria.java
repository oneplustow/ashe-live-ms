package cn.oneplustow.mc.entity.criteria;

import cn.oneplustow.common.web.page.PageDomain;
import cn.oneplustow.config.db.model.PageQuery;
import cn.opl.annotation.Query;
import cn.opl.enums.QueryType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信对象 rj_interior_message
 *
 * @author cc
 * @date 2021-10-02
 */
@Data
public class InteriorMessageCriteria extends PageQuery {

    private static final long serialVersionUID=1L;

    /**
     * 标题
     */
    @Query(QueryType.LIKE)
    private String title;

    /**
     * 状态
     */
	@Query
	private String status;


    /**
     * 接收人ID
     */
	@Query
	private Long toUser;

    /**
     * 创建时间
     */
	@Query(value = QueryType.GT_EQ,fieldName = "create_Time")
	private Date createStartTime;
    /**
     * 创建时间
     */
	@Query(value = QueryType.LT_EQ,fieldName = "create_Time")
	private Date createEndTime;


}
