package cn.oneplustow.sc.entity;

import cn.oneplustow.config.db.model.SysBaseEntity;
import cn.oneplustow.sc.entity.vo.MetaVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门表 sys_dept
 * 
 * @author ruoyi
 */
public class SysDept extends SysBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private String orderNum;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 父部门名称 */
    private String parentName;
    
    /** 子部门 */
    private List<SysDept> children = new ArrayList<SysDept>();

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getAncestors()
    {
        return ancestors;
    }

    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }

    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    @NotBlank(message = "显示顺序不能为空")
    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getLeader()
    {
        return leader;
    }

    public void setLeader(String leader)
    {
        this.leader = leader;
    }

    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public List<SysDept> getChildren()
    {
        return children;
    }

    public void setChildren(List<SysDept> children)
    {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("deptName", getDeptName())
            .append("orderNum", getOrderNum())
            .append("leader", getLeader())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }

    /**
     * 路由配置信息
     *
     * @author ruoyi
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class RouterVo
    {
        /**
         * 路由名字
         */
        private String name;

        /**
         * 路由地址
         */
        private String path;

        /**
         * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
         */
        private String hidden;

        /**
         * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
         */
        private String redirect;

        /**
         * 组件地址
         */
        private String component;

        /**
         * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
         */
        private Boolean alwaysShow;

        /**
         * 其他元素
         */
        private MetaVo meta;

        /**
         * 子路由
         */
        private List<RouterVo> children;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getPath()
        {
            return path;
        }

        public void setPath(String path)
        {
            this.path = path;
        }

        public String getHidden()
        {
            return hidden;
        }

        public void setHidden(String hidden)
        {
            this.hidden = hidden;
        }

        public String getRedirect()
        {
            return redirect;
        }

        public void setRedirect(String redirect)
        {
            this.redirect = redirect;
        }

        public String getComponent()
        {
            return component;
        }

        public void setComponent(String component)
        {
            this.component = component;
        }

        public Boolean getAlwaysShow()
        {
            return alwaysShow;
        }

        public void setAlwaysShow(Boolean alwaysShow)
        {
            this.alwaysShow = alwaysShow;
        }

        public MetaVo getMeta()
        {
            return meta;
        }

        public void setMeta(MetaVo meta)
        {
            this.meta = meta;
        }

        public List<RouterVo> getChildren()
        {
            return children;
        }

        public void setChildren(List<RouterVo> children)
        {
            this.children = children;
        }
    }
}
