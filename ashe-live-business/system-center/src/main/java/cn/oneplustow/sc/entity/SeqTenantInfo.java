package cn.oneplustow.sc.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 业务编码使用者信息
 * @author cc
 *
 */
@Data
@TableName("seq_tenantInfo")
public class SeqTenantInfo {
    private String tenantEntityId;

    private String tenantEntityName;

    public SeqTenantInfo(String tenantEntityId, String tenantEntityName) {
        this.tenantEntityId = tenantEntityId;
        this.tenantEntityName = tenantEntityName;
    }
}
