package my;

import my.config.OpenWeatherMapConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author gusev.aleksandr
 * @since 24.09.2017
 */
@EnableConfigurationProperties(OpenWeatherMapConfig.class)
@Configuration
public class AppConfig {
}
