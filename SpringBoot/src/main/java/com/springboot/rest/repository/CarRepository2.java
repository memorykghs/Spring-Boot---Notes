package com.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.entity.Car2;
import com.springboot.rest.entity.CarPK2;

@Repository
public interface CarRepository2 extends JpaRepository<Car2, CarPK2>{

}
