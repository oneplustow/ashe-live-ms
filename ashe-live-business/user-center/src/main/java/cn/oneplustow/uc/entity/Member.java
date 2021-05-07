package cn.oneplustow.uc.entity;

import cn.oneplustow.config.db.model.BaseEntity;
import lombok.Data;

/**
 * 会员对象
 * 
 * @author cc
 */
@Data
public class Member extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户账号 */
    private String account;

    /** 用户昵称 */
    private String nickName;

    private Long userId;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

}
