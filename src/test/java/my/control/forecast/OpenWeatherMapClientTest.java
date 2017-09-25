package my.control.forecast;

import my.App;
import my.config.OpenWeatherMapConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * TODO: comment
 * @author gusev.aleksandr
 * @since 24.09.2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { App.class, OpenWeatherMapClient.class })
public class OpenWeatherMapClientTest {

	private final String CITY_NAME = "city";
	private final String CNT_NAME = "cnt";
	private final String APPID_NAME = "appid";
	private final String GET_FORECAST_REQUEST = "http://api.openweathermap.org/data/2.5/forecast?q=%s&units=metric&cnt=%s&APPID=%s";
	private final String GET_FORECAST_RESPONSE = "{success: \"success\"}";
	private final String CHECK_CITY_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s";
	private final String CHECK_CITY_RESPONSE = "{success: \"cityExist\"}";

	private MockRestServiceServer mockServer;

	@Autowired
	private OpenWeatherMapClient openWeatherMapClient;


	@Autowired
	private OpenWeatherMapConfig openWeatherMapConfig;

	@Before
	public void setUp() throws Exception {
		mockServer = MockRestServiceServer.createServer(openWeatherMapClient.getRestTemplate());
	}

	@Test
	public void getForecastForCityTest() throws Exception {
		mockServer.expect(requestTo(String.format(GET_FORECAST_REQUEST, CITY_NAME, openWeatherMapConfig.getCnt(), openWeatherMapConfig.getAppid())))
				.andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess(GET_FORECAST_RESPONSE, MediaType.APPLICATION_JSON));

		String forecast = openWeatherMapClient.getForecastForCity(CITY_NAME);
		assertEquals(GET_FORECAST_RESPONSE, forecast);
	}

	@Test
	public void isCityExistTest() throws Exception {
		mockServer.expect(requestTo(String.format(CHECK_CITY_REQUEST, CITY_NAME, openWeatherMapConfig.getAppid())))
				.andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess(CHECK_CITY_RESPONSE, MediaType.APPLICATION_JSON));

		assertTrue(openWeatherMapClient.isCityExist(CITY_NAME));

	}

}