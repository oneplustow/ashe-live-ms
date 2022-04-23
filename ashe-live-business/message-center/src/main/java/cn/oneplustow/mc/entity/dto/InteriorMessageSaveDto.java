package cn.oneplustow.mc.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 站内信业务对象 rj_interior_message
 *
 * @author cc
 * @date 2021-10-02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("站内信业务对象")
public class InteriorMessageSaveDto {

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
