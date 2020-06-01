package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImp implements web.service.CarService {
    int count = 3;
    @Override
    public List<Car> getRandomCars() {
        List<Car> cars = new ArrayList<>();
        for(int i = 1; i<= count; i++){
            cars.add(new Car((long) i,"car"+i, "number"+i ) );
        }
        return cars;
    }
}
