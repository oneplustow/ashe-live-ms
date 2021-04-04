package cn.oneplustow.common.enume;


/**
 * 业务编码重置类型
 * @author greatmap
 *
 */
public enum ResetType {
	/**
	 * 0：每天重置
	 */
    DAY(0,"每天重置"),
    /**
	 * 1：每月重置
	 */
    MONTH(1,"每月重置"),
    /**
	 * 2：每年重置
	 */
    YEAR(2,"每年重置");

    private Integer code;

    private String value;

    ResetType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return this.code;
    }
    
    public String getValue() {
		return value;
	}

    public ResetType getByCode(Integer code){
        if(code != null){
            for(ResetType type : ResetType.values()){
                if(type.getCode().equals(code)){
                    return type;
                }
            }
        }
        return null;
    }
}
