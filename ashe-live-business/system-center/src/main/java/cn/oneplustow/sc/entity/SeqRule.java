package cn.oneplustow.sc.entity;

import cn.oneplustow.common.enume.PaddingType;
import cn.oneplustow.common.enume.ResetType;
import cn.oneplustow.common.enume.RuleType;
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
 * @since 2022-03-10
 */
@Data
@TableName("seq_rule")
public class SeqRule implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value = "ID",type = IdType.AUTO)
	/**规则id*/
	private Integer id;

	/**编码id*/
	private String seqId;

	/**规则顺序号*/
	private Integer ruleOrder;

	/**规则类型*/
	private RuleType ruleType;

	/**规则参数*/
	private String ruleValue;

	/**补齐方向*/
	private PaddingType paddingSide;

	/**补齐长度*/
	private Integer paddingWidth;

	/**补齐内容*/
	private String paddingChar;

	/**起始序号*/
	private Long startNo;

	/**步长*/
	private Integer step;

	/**当前序号*/
	private Long currentNo;

	/**重置标志*/
	private String resetFlag;

	/**重置类型*/
	private ResetType resetType;

    /**版本（乐观锁）*/
    @Version
    private Integer version;

	public SeqRule() {
	}

	public SeqRule(String seqId) {
		this.seqId = seqId;
	}
}