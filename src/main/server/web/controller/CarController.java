package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImp;

import java.util.List;

@Controller
@RequestMapping("/")
public class CarController {

    @RequestMapping(value = "cars", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printCars(@RequestParam(required = false) String locale) {
        CarService carService = new CarServiceImp();
        List<Car> cars = carService.getRandomCars();
        ModelAndView result = new ModelAndView("/cars");
        if (locale != null && locale.equals("ru")) {
            result.addObject("title", "МАШИНЫ");
        } else {
            result.addObject("title", "CARS");
        }
        result.addObject("cars", cars);
        return result;
    }
}
