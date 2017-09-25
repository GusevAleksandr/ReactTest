package my.model.dao;

import my.model.pojo.RequestForecast;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * DAO для работы с таблицей RequestForecast
 * Используем обычный CRUD-репозиторий, т.к. нам достаточно
 * элементарных функций для работы с таблицей
 * @author gusev.aleksandr
 * @since 22.09.2017
 */
@Transactional
public interface RequestForecastDao extends CrudRepository<RequestForecast, Long> {

	/**
	 * Ищем результат запроса по названию города
	 * @param cityName город
	 * @return данные из БД, в случае если данные не найдены, вернет null
	 */
	public RequestForecast findByCityName(String cityName);
}
