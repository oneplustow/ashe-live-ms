package cn.oneplustow.sc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cc
 * @date 14/09/2020 13:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private String id;
    private String userName;
    private String password;
}
