package my.control;

import my.control.forecast.WeatherMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Основной контроллер
 * @author gusev.aleksandr
 * @since 22.09.2017
 */
@Controller
public class MainController {

    @Autowired
    private WeatherMap openWeatherMapClient;

    @RequestMapping("/greeting")
    public ModelAndView indexPage() {
        return new ModelAndView("greeting");
    }

    @RequestMapping(value = "/requestForecast", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String requestForecast(String city) {
        if (city == null || city.isEmpty() || !openWeatherMapClient.isCityExist(city)) {
            throw new IllegalArgumentException("Не найден введенный город");
        }

        return openWeatherMapClient.getForecastForCity(city);
    }
    
}
