package cn.oneplustow.common.enume;

/**
 * 业务编码回收类型
 * @author cc
 *
 */
public enum RecycledType {
	/**
	 *NO：0：不可回收
	 */
    NO(0,"不可回收"),
    
    /**
	 *YES：1：可回收
	 */
    YES(1,"可回收");

    private Integer code;

    private String value;

    RecycledType(Integer code,String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return this.code;
    }
    
    public String getValue() {
		return value;
	}

    public RecycledType getByCode(Integer code){
        if(code != null){
            for(RecycledType type : RecycledType.values()){
                if(type.getCode().equals(code)){
                    return type;
                }
            }
        }
        return null;
    }
}
