package my.model.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * TODO: comment
 * @author gusev.aleksandr
 * @since 22.09.2017
 */
@Entity
@Table(name = "request_forecast")
public class RequestForecast {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String cityName;

	@Lob
	@NotNull
	private String forecast;

	public RequestForecast() { }

	public RequestForecast(long id) {
		this.id = id;
	}

	public RequestForecast(String cityName, String forecast) {
		this.cityName = cityName;
		this.forecast = forecast;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
}