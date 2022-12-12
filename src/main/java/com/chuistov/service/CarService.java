package com.chuistov.service;

import com.chuistov.model.Car;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CarService {

    private static final List<Car> CAR_LIST = List.of(
            new Car("Volvo", "S60", 2019),
            new Car("Lada", "Vesta", 2017),
            new Car("Nissan", "Almera", 2013),
            new Car("Mercedes", "S600", 2005),
            new Car("Tesla", "Model 5", 2021)
    );

    private static final int MAX_CAR_NUMBER = 5;

    public static List<Car> getNCars(int n) {
        if (n <= 0) {
            return Collections.emptyList();
        } else if (n < MAX_CAR_NUMBER) {
            return CAR_LIST.subList(0, n);
        } else {
            return CAR_LIST;
        }
    }
}
