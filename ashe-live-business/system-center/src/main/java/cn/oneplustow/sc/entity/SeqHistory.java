package cn.oneplustow.sc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 编码历史表
 * </p>
 *
 * @author cc
 * @since 2022-03-10
 */
@Data
@TableName("seq_history")
public class SeqHistory implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(type = IdType.UUID)
	/**id*/
	private String id;

	/**"编码id"*/
	private String seqid;

	/**编码值*/
	private String seqCode;

	/**实体名*/
	private String tenantEntityName;

	/**实体Id*/
	private String tenantEntityId;

	@TableField(exist = false)
	/**编码名称*/
	private String seqName;

	public SeqHistory() {
	}

	public SeqHistory(String seqid, String seqCode) {
		this.seqid = seqid;
		this.seqCode = seqCode;
	}

	public SeqHistory(String seqid, String seqCode, String tenantEntityId, String tenantEntityName) {
		this.tenantEntityId = tenantEntityId;
		this.tenantEntityName = tenantEntityName;
		this.seqCode = seqCode;
		this.seqid = seqid;
	}

}