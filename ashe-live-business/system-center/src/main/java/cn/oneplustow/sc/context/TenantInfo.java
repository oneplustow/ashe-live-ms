package cn.oneplustow.sc.context;


/**
 * 业务编码使用者信息
 * @author cc
 *
 */
public class TenantInfo {
    private String tenantEntityId;

    private String tenantEntityName;

    public TenantInfo(String tenantEntityId, String tenantEntityName) {
        this.tenantEntityId = tenantEntityId;
        this.tenantEntityName = tenantEntityName;
    }

    public String getTenantEntityId() {
        return tenantEntityId;
    }

    public void setTenantEntityId(String tenantEntityId) {
        this.tenantEntityId = tenantEntityId;
    }

    public String getTenantEntityName() {
        return tenantEntityName;
    }

    public void setTenantEntityName(String tenantEntityName) {
        this.tenantEntityName = tenantEntityName;
    }
}
