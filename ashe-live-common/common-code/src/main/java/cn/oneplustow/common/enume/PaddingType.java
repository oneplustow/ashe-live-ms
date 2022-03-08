package cn.oneplustow.common.enume;


/**
 * 业务编码规则补齐方向类型
 * @author cc
 *
 */
public enum PaddingType {
	/**
	 * 0：左
	 */
    LEFT(0,"左"),
    
    /**
     * 1：右
     */
    RIGHT(1,"右");

    private Integer code;

    private String value;

    PaddingType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return this.code;
    }
    
    public String getValue() {
		return value;
	}

	public PaddingType getByCode(Integer code){
        if(code != null){
            for(PaddingType type : PaddingType.values()){
                if(type.getCode().equals(code)){
                    return type;
                }
            }
        }
        return null;
    }
}
