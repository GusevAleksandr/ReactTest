package my.control.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Объект для запроса данных через RestTemplate
 * @author gusev.aleksandr
 * @since 22.09.2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRequestEntity {
}
