package cn.oneplustow.sc.entity;


import cn.oneplustow.common.enume.RecycledType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 业务编码
 * </p>
 *
 * @author cc
 * @since 2022-03-10
 */
@Data
@TableName("seq_sequence")
public class Sequence implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value = "ID",type = IdType.UUID)
	/**编码id*/
	private String id;

	/**编码名称*/
	private String seqName;

	/**分隔符*/
	private String delimiter;

	/**是否可回收*/
	private RecycledType needRecycle;

	@TableField(exist = false)
	private List<SeqRule> seqRules;

	public Sequence(String seqName) {
		this.seqName = seqName;
	}

	public Sequence() {
	}
}