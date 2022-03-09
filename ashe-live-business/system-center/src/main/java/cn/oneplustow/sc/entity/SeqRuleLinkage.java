package cn.oneplustow.sc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 编码规则
 * </p>
 *
 * @author cc
 * @since 2017-08-10
 */
@Data
@TableName("seq_rule_linkage")
public class SeqRuleLinkage implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value = "ID",type = IdType.AUTO)
	/**联动id*/
	private Integer id;

	/**规则id*/
	private Integer ruleId;

	/**编码id*/
	private String seqId;

	/**联动key*/
	private String linkageFlag;

	/** 当前value*/
	private Long linkageValue;

    /**版本（乐观锁）*/
    @Version
    private Integer version;

	public SeqRuleLinkage(Integer ruleId, String seqId, String linkageFlag) {
		this.ruleId = ruleId;
		this.seqId = seqId;
		this.linkageFlag = linkageFlag;
	}


}