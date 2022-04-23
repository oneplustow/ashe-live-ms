package cn.oneplustow.mc.entity.criteria;

import cn.oneplustow.config.db.model.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 站内信业务对象 rj_interior_message
 *
 * @author cc
 * @date 2021-10-02
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("站内信业务对象")
public class InteriorMessageListCriteria extends PageQuery {

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 发送人ID
     */
    @ApiModelProperty(value = "发送人ID")
    private Long fromUser;

    /**
     * 接收人ID
     */
    @ApiModelProperty(value = "接收人ID")
    private Long toUser;

    /**
     * 跳转链接
     */
    @ApiModelProperty(value = "跳转链接")
    private String skipLink;


}
