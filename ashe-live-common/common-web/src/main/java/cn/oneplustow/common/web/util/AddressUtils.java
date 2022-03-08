package cn.oneplustow.common.web.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HttpUtil;
import cn.oneplustow.common.config.RuoYiConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 获取地址类
 * 
 * @author ruoyi
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "http://apis.juhe.cn/ip/ipNew?ip={}&key={}";

    public static String getRealAddressByIP(String ip)
    {
        List<String> ips = StrUtil.split(ip, ',');
        ip = ips.get(0);
        String address = "XX XX";
        // 内网不查询
        if (IpUtils.internalIp(ip))
        {
            return "内网IP";
        }
        RuoYiConfig config = SpringUtil.getBean(RuoYiConfig.class);
        if (StrUtil.isBlank(config.getJuheApiIpKey())) {
            log.warn("无法获取聚合数据请求key");
        }

        String rspStr = HttpUtil.get(StrUtil.format(IP_URL,ip,config.getJuheApiIpKey()));
        if (!JSONValidator.from(rspStr).validate()){
            log.error("获取地理位置异常 {}", ip);
            return address;
        }
        JSONObject obj = JSONObject.parseObject(rspStr);
        int resultcode = obj.getIntValue("resultcode");
        if(resultcode != 200){
            log.error("位置信息获取json异常： {}", rspStr);
            return address;
        }
        JSONObject data = obj.getObject("result", JSONObject.class);
        String country = data.getString("Country");
        String region = data.getString("Province");
        String city = data.getString("City");
        String Isp = data.getString("Isp");
        address = StrUtil.join("-",country,region,city,Isp);
        return address;
    }
}
