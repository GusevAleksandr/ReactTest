package my.control.forecast;

import my.model.dao.RequestForecastDao;
import my.model.pojo.RequestForecast;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * TODO: comment
 * @author gusev.aleksandr
 * @since 19.09.2017
 */
@Service
public class OpenWeatherMapClient implements WeatherMap, InitializingBean {

	private static final String GET_FORECAST_REQUEST = "http://api.openweathermap.org/data/2.5/forecast?q={city},RU&units=metric&cnt={cnt}&APPID={appid}";
	private static final String CHECK_CITY = "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={appid}";

	private RestTemplate restTemplate;
	private CloseableHttpClient httpclient;

	@Autowired
	private RequestForecastDao userDao;

	@Override
	public void afterPropertiesSet() throws Exception {
		httpclient = HttpClients.custom().build();
		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpclient));
	}

	@Override
	public String getForecastForCity(String city) {
		String result;
		RequestForecast forecast = userDao.findByCityName(city);
		if (forecast == null) {
			result = requestOnOPWM(GET_FORECAST_REQUEST, city, "144", "a84b93a899d204bf32e0aad3d7086f97");
			addForecastInTable(city, result);
		} else {
			result = forecast.getForecast();
		}
		return result;
	}

	@Override
	public boolean isCityExist(String city) {
		String result = requestOnOPWM(CHECK_CITY, city, "a84b93a899d204bf32e0aad3d7086f97");
		return result != null;
	}

	private void addForecastInTable(String cityname, String forecast) {
		userDao.save(new RequestForecast(cityname, forecast));
	}

	private String requestOnOPWM(String request, String... params) {
		String response = null;
		try {
			response = restTemplate.postForObject(request,
					new HttpEntity<JsonRequestEntity>(new JsonRequestEntity()), String.class, params);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return response;
	}
}
