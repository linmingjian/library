package com.linmingjian.library.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Spring Security 相关参数
 *
 * @author lin
 * @since 2023-05-27
 */
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    /** token 请求头 */
    private String tokenHeader;

    /** token 请求头前缀 */
    private String tokenStartWith;

    /** jwt 加密key */
    private String tokenKey;

    /** jwt 过期时间 */
    private int tokenValidityInSeconds;

    /** RSA 私钥 */
    private String privateKey;

    /** RSA 公钥 */
    private String publicKey;
}
