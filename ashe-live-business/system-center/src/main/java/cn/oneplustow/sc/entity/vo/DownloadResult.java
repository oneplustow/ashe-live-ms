package cn.oneplustow.sc.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CC
 * @title: DownloadResult
 * @description:
 * @date 2021/8/2022:24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DownloadResult {
	private String fileName;

	private long dataCount;
}
