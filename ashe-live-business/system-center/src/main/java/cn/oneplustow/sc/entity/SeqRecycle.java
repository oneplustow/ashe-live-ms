package cn.oneplustow.sc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 序列表回收表
 * </p>
 *
 * @author cc
 * @since 2017-08-10
 */
@Data
@TableName("seq_recycle")
public class SeqRecycle implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value = "ID",type = IdType.UUID)
	/**id*/
	private String id;

	/**编码id*/
	private String seqId;

	/**编码*/
	private String seqCode;

	/**回收状态*/
	private Boolean isRecycled;

	@TableField(exist = false)
	/**序列号名称*/
	private String seqName;

	public SeqRecycle() {
	}

	public SeqRecycle(String seqCode) {
		this.seqCode = seqCode;
	}

	public SeqRecycle(String seqId, String seqCode) {
		this.seqId = seqId;
		this.seqCode = seqCode;
	}

	public SeqRecycle(String seqId, Boolean isRecycled) {
		this.seqId = seqId;
		this.isRecycled = isRecycled;
	}
}