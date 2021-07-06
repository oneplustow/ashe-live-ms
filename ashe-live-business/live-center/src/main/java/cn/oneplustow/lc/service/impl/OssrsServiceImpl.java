package cn.oneplustow.lc.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.lc.handler.OssrsCallBackHandler;
import cn.oneplustow.lc.handler.OssrsCallBackHandlerFactory;
import cn.oneplustow.lc.service.IOssrsService;
import cn.oneplustow.lc.vo.OssrsCallBackDto;
import cn.oneplustow.lc.vo.OssrsSystemSummariesDto;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CC
 * @title: OssrsServiceImpl
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1622:47
 */
@Slf4j
@Service("ossrsService")
public class OssrsServiceImpl implements IOssrsService {


    private static final String URL_TEMPLATE = "http://{}:{}/api/v1/";
    private static final String SUMMARIES = "summaries";
    private static final String CLIENTS = "clients";

    /**成功的情况下返回给ossrs服务器的*/
    private static final int SUCCESS = 0;
    /**失败情况下返回给ossrs服务器*/
    private static final int FAIL = 1;

    @Autowired
    private OssrsCallBackHandlerFactory callBackHandlerFactory;

    @Override
    public int callBack(OssrsCallBackDto ossrsCallBackDto) {
        OssrsCallBackHandler ossrsCallBackHandler = callBackHandlerFactory.getOssrsCallBackHandler(ossrsCallBackDto.getAction());
        return ossrsCallBackHandler.callBack(ossrsCallBackDto) ? SUCCESS : FAIL;
    }


    @Override
    public boolean eliminateStream(String ip, Integer port, String clientId){
        StringBuilder url = new StringBuilder(StrUtil.format(URL_TEMPLATE,ip,port));
        url.append(CLIENTS).append("/").append(clientId);
        HttpResponse execute = HttpUtil.createRequest(Method.DELETE, url.toString()).execute();
        return true;
    }

    @Override
    public OssrsSystemSummariesDto getOssrsServer(String ip, Integer port){
        String serverJsonRequest = requestOssrsServer(ip, port, SUMMARIES);
        boolean validate = JSONValidator.from(serverJsonRequest).validate();
        if(!validate){
            throw new WarningMessageException(StrUtil.format("JSON解析错误:{}",serverJsonRequest));
        }
        JSONObject serverRequest = JSONObject.parseObject(serverJsonRequest);
        int code = serverRequest.getIntValue("code");
        if(code != SUCCESS){
            return null;
        }
        OssrsSystemSummariesDto systemSummariesDto = serverRequest.getJSONObject("data")
                .getObject("system", OssrsSystemSummariesDto.class);
        return systemSummariesDto;
    }

    @Override
    public boolean connectinoOssrsServer(String ip, Integer port) {
        String serverJsonRequest = requestOssrsServer(ip, port, null);
        boolean validate = JSONValidator.from(serverJsonRequest).validate();
        if(!validate){
            log.error("JSON解析错误:{}",serverJsonRequest);
            return false;
        }
        JSONObject serverRequest = JSONObject.parseObject(serverJsonRequest);
        int code = serverRequest.getIntValue("code");
        String serverId = serverRequest.getString("server");
        //如果状态和serviceid 都OK 则说明服务器在线
        return (code == 0 && StrUtil.isNotBlank(serverId));
    }


    private String requestOssrsServer(String ip,Integer port,String path){
        String url = StrUtil.format(URL_TEMPLATE,ip,port);
        if(StrUtil.isNotBlank(path)){url = url + path;}
        //这里如果解析错误，则说明返回的不是json
        String serverJsonRequest = HttpUtil.get(url);
        return serverJsonRequest;
    }


}
