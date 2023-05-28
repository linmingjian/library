package com.linmingjian.library.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 相关参数
 *
 * @author lin
 * @since 2023-05-27
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    /* 是否启用 Swagger **/
    private boolean enabled;
}
