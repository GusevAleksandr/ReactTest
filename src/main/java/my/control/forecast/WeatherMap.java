package my.control.forecast;

/**
 * Описание сервиса для полуеения данных о прогнозе погоды
 * будем отдавать json по эталону сайта openweathermap.org
 * Т.е. если будем расширять данный интерфейс для другой API, то необходимо будет
 * приводить json к подобию openweathermap.org
 * @author gusev.aleksandr
 * @since 22.09.2017
 */
public interface WeatherMap {

	/**
	 * Получение данных о прогнозе погоды в виде json
	 * @param city город
	 * @return данные с сайта
	 */
	String getForecastForCity(String city);

	/**
	 * Проверка существования города
	 * @param city город
	 * @return true - город найден, false - введено некорректное название города
	 */
	boolean isCityExist(String city);
}
