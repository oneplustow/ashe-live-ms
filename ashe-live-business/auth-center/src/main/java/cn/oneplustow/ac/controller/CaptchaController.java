package cn.oneplustow.ac.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.IdUtil;
import cn.oneplustow.common.constant.Constants;
import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.config.redis.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author Lion Li
 */
@Api(value = "验证码操作处理", tags = {"验证码管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
public class CaptchaController {


    /**
     * 生成验证码
     */
    @ApiOperation("生成验证码")
    @GetMapping("/captchaImage")
    public AjaxResult getCode() {
        Map<String, Object> ajax = new HashMap<>();
        ajax.put("captchaOnOff", false);
        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        // 生成验证码
        Integer length = 6;
        CodeGenerator codeGenerator = new RandomGenerator(length);
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(160, 60);
        captcha.setBackground(Color.PINK);
        captcha.setFont( new Font("Arial", Font.BOLD, 48));
        captcha.setGenerator(codeGenerator);
        captcha.createCode();
        String code = captcha.getCode();
        RedisUtils.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        ajax.put("uuid", uuid);
        ajax.put("img", captcha.getImageBase64());
        return AjaxResult.success(ajax);
    }


}
