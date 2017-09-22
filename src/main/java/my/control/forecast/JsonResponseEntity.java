package my.control.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;

/**
 * TODO: comment
 * @author gusev.aleksandr
 * @since 19.09.2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonResponseEntity {
	private HashMap<String, String> city;
	private List<HashMap<String, Integer>> list;

	public HashMap<String, String> getCity() {
		return city;
	}

	public void setCity(HashMap<String, String> city) {
		this.city = city;
	}

	public List<HashMap<String, Integer>> getList() {
		return list;
	}

	public void setList(List<HashMap<String, Integer>> list) {
		this.list = list;
	}
}
