package my.control;

import my.control.forecast.WeatherMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private WeatherMap openWeatherMapClient;
    
    @RequestMapping("/greeting")
    public ModelAndView page() {
        return new ModelAndView("greeting");
    }

    @RequestMapping("/testAjax")
    public String testAjax() {
        return "success";
    }

    @RequestMapping(value = "/testAjax2", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String testAjax2(String value1) {
        return openWeatherMapClient.getForecastForCity(value1);
    }
    
}
