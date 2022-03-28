package cn.oneplustow.mc.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信对象 rj_interior_message
 *
 * @author cc
 * @date 2021-10-02
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("mc_interior_message")
public class InteriorMessage implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态
     */
    private String status;

    /**
     * 发送人ID
     */
    private Long fromUser;

    /**
     * 接收人ID
     */
    private Long toUser;

    /**
     * 跳转链接
     */
    private String skipLink;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

}
