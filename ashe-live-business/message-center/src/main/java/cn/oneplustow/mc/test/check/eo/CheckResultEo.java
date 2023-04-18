package cn.oneplustow.mc.test.check.eo;

import java.util.Date;

public class CheckResultEo {
    private Long id;
    private String domain;
    private String field;
    private String standardValues;
    private String standardConverts;
    private String dataType;
    private Boolean isAllowNull;
    private Integer resultType;
    private String remark;
    private String dataIds;
    private String orgValue;

//    private Date createTm = new Date();
//
//    public Date getCreateTm() {
//        return createTm;
//    }
//
//    public void setCreateTm(Date createTm) {
//        this.createTm = createTm;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAllowNull() {
        return isAllowNull;
    }

    public void setAllowNull(Boolean allowNull) {
        isAllowNull = allowNull;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getStandardValues() {
        return standardValues;
    }

    public void setStandardValues(String standardValues) {
        this.standardValues = standardValues;
    }

    public String getStandardConverts() {
        return standardConverts;
    }

    public void setStandardConverts(String standardConverts) {
        this.standardConverts = standardConverts;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDataIds() {
        return dataIds;
    }

    public void setDataIds(String dataIds) {
        this.dataIds = dataIds;
    }

    public String getOrgValue() {
        return orgValue;
    }

    public void setOrgValue(String orgValue) {
        this.orgValue = orgValue;
    }
}
