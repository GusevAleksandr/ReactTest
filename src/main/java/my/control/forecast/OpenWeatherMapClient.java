package my.control.forecast;

import my.config.OpenWeatherMapConfig;
import my.model.dao.RequestForecastDao;
import my.model.pojo.RequestForecast;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Сервис для получения данных по API с сайта openweathermap.org
 * @author gusev.aleksandr
 * @since 22.09.2017
 */
@Service
public class OpenWeatherMapClient implements WeatherMap, InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(OpenWeatherMapClient.class);

	private static final String GET_FORECAST_REQUEST = "http://api.openweathermap.org/data/2.5/forecast?q={city}&units=metric&cnt={cnt}&APPID={appid}";
	private static final String CHECK_CITY = "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={appid}";

	private RestTemplate restTemplate;
	private CloseableHttpClient httpclient;

	@Autowired
	private RequestForecastDao userDao;

	@Autowired
	private OpenWeatherMapConfig openWeatherMapConfig;

	@Override
	public void afterPropertiesSet() throws Exception {
		httpclient = HttpClients.custom().build();
		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpclient));
	}

	@Override
	public String getForecastForCity(String city) {
		String result;
		//пытаемся получить данные из БД
		RequestForecast forecast = userDao.findByCityName(city);
		if (forecast == null) {
			//если в БД ничего нет, то делаем запрос к сайту
			result = requestOnOPWM(GET_FORECAST_REQUEST, city, openWeatherMapConfig.getCnt(), openWeatherMapConfig.getAppid());
			addForecastInTable(city, result);
		} else {
			log.info("Для города {} прогооз взят из БД", city);
			result = forecast.getForecast();
		}
		return result;
	}

	@Override
	public boolean isCityExist(String city) {
		String result = requestOnOPWM(CHECK_CITY, city, openWeatherMapConfig.getAppid());
		return result != null;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	/**
	 * Добавляем прогноз в таблицу
	 * @param cityname город
	 * @param forecast прогноз
	 */
	private void addForecastInTable(String cityname, String forecast) {
		userDao.save(new RequestForecast(cityname, forecast));
	}

	/**
	 * Произведение запроса к сайту по API
	 * @param request шаблон запроса
	 * @param params GET-параметры запроса
	 * @return полученный результат
	 */
	private String requestOnOPWM(String request, String... params) {
		String response = null;
		try {
			response = restTemplate.postForObject(request,
					new HttpEntity<JsonRequestEntity>(new JsonRequestEntity()), String.class, params);
			log.info("По параметрам ({}) успешно получен результат с сайта", params);
		} catch (Exception ex) {
			log.error("Возникли ошибки при вылллнении запроса на сайте по параметрам ({})", params, ex);
		}

		return response;
	}
}
