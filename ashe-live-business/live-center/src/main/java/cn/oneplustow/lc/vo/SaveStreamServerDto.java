package cn.oneplustow.lc.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author cc
 */
@Data
public class SaveStreamServerDto implements Serializable {

    /** 流服务器ID */
    private Integer id;

    /** 流服务器名称 */
    @NotBlank(message = "服务器名称不能为空")
    private String serverName;

    /** 服务器IP */
    @NotBlank(message = "服务器IP不能为空")
    private String ip;

    /** 服务器端口 */
    @NotBlank(message = "服务器端口不能为空")
    private Integer port;

}
