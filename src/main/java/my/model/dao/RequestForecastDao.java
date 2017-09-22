package my.model.dao;

import my.model.pojo.RequestForecast;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * TODO: comment
 * @author gusev.aleksandr
 * @since 22.09.2017
 */
@Transactional
public interface RequestForecastDao extends CrudRepository<RequestForecast, Long> {

	public RequestForecast findByCityName(String cityName);
}
