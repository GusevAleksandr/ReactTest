package my.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Класс-конфигурация для настройки работы с сайтом
 * @author gusev.aleksandr
 * @since 24.09.2017
 */
@ConfigurationProperties("app.weatherMap")
public class OpenWeatherMapConfig {
	private String cnt = "144";
	private String appid = "";


	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
}
