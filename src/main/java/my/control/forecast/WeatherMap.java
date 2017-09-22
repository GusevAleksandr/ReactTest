package my.control.forecast;

/**
 * TODO: comment
 * @author gusev.aleksandr
 * @since 20.09.2017
 */
public interface WeatherMap {

	String getForecastForCity(String city);

	boolean isCityExist(String city);
}
