package cn.oneplustow.live.vo;

import cn.oneplustow.common.annoatation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
