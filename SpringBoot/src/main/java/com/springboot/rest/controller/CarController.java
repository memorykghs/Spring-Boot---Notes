package com.springboot.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.entity.Car2;
import com.springboot.rest.entity.CarPK2;
import com.springboot.rest.repository.CarRepository2;

@RestController
public class CarController {

	@Autowired
	private CarRepository2 carRepository2;

	public Optional<Car2> findById(@RequestBody RequestEntity<Car2> requestEntity) {

		Car2 car2 = requestEntity.getBody();

		CarPK2 carPK2 = new CarPK2();
		carPK2.setManufacturer(car2.getManufacturer());
		carPK2.setType(car2.getType());
		carPK2.setSeries(car2.getSeries());

		return carRepository2.findById(carPK2);
	}
}
