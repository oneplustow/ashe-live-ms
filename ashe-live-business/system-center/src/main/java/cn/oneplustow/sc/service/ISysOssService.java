package cn.oneplustow.sc.service;


import cn.oneplustow.config.db.model.TableDataInfo;
import cn.oneplustow.config.db.mybatisplus.core.IServicePlus;
import cn.oneplustow.sc.entity.SysOss;
import cn.oneplustow.sc.entity.bo.SysOssBo;
import cn.oneplustow.sc.entity.vo.DownloadResult;
import cn.oneplustow.sc.entity.vo.SysOssVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * 文件上传 服务层
 *
 */
public interface ISysOssService extends IServicePlus<SysOss> {

	TableDataInfo<SysOss> queryPageList(SysOssBo sysOss);

	SysOssVo upload(MultipartFile file);

	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    String previewing(Long ossId);

	DownloadResult downloadOssFile(String downloadToken, OutputStream outputStream, Consumer<SysOss> consumer);
}
