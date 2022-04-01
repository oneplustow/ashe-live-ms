package cn.oneplustow.mc.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 站内信视图对象 rj_interior_message
 *
 * @author cc
 * @date 2021-10-02
 */
@Data
@ApiModel("站内信视图对象")
public class InteriorMessageVo {

	private static final long serialVersionUID = 1L;

	/**
     *  id
     */
	@ApiModelProperty("id")
	private Long id;

    /**
     * 标题
     */
	@ApiModelProperty("标题")
	private String title;

    /**
     * 内容
     */
	@ApiModelProperty("内容")
	private String content;

    /**
     * 已读标识
     */
	@ApiModelProperty("已读标识")
	private String alreadyRead;

    /**
     * 发送人ID
     */
	@ApiModelProperty("发送人ID")
	private Long fromUser;

    /**
     * 接收人ID
     */
	@ApiModelProperty("接收人ID")
	private Long toUser;

    /**
     * 跳转链接
     */
	@ApiModelProperty("跳转链接")
	private String skipLink;


	@ApiModelProperty("创建时间")
	private Date createTime;

}
