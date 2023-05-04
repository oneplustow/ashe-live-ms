package cn.oneplustow.mc.test.check.bean;

public class FieldRule {
    private String field;
    private String fieldDataType;
    private Integer isAllowNull = 1;
    /**
     * 标准值，eg:1,2,3
     */
    private String values;
    /**
     * 标准转换，eg:1-10,2-20,3-30
     */
    private String converts;

    public FieldRule(String field,String fieldDataType, Integer isAllowNull, String values, String convert) {
        this.field=field;
        this.fieldDataType=fieldDataType;
        this.isAllowNull=isAllowNull;
        this.values = values;
        this.converts =convert;
    }

    public String getFieldDataType() {
        return fieldDataType;
    }

    public void setFieldDataType(String fieldDataType) {
        this.fieldDataType = fieldDataType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getIsAllowNull() {
        return isAllowNull;
    }

    public void setIsAllowNull(Integer isAllowNull) {
        this.isAllowNull = isAllowNull;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getConverts() {
        return converts;
    }

    public void setConverts(String converts) {
        this.converts = converts;
    }
}
}
