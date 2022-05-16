package cn.oneplustow.common.enume;


/**
 * 业务编码生成规则类型
 * @author cc
 *
 */
public enum RuleType {
	
	/**
	 * 0：常量
	 */
    CONST(0,"常量"), 
    
    /**
	 * 1：日期
	 */  
    DATETIME(1,"日期"),
    
    /**
	 * 2：步进数值
	 */   
    NUMBER(2,"步进数值"),
    
    /**
	 * 3：函数生成
	 */
    FUNCTION(3,"函数生成"),
    
    /**
	 * 4：随机字符
	 */
    RANDOM(4,"随机字符"),

    /**
     * 5：步进数值
     */
    LETTER(5,"步进字母"),

    /**
     * 6：占位字符
     */
    PLACEHOLDER(6,"占位字符");

    private Integer code;

    private String value;

    RuleType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return this.code;
    }
    
    public String getValue() {
		return value;
	}

    public RuleType getByCode(Integer code){
        if(code != null){
            for(RuleType type : RuleType.values()){
                if(type.getCode().equals(code)){
                    return type;
                }
            }
        }
        return null;
    }
}
