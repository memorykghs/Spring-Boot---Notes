package com.springboot.rest.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "CARS")
@IdClass(value = CarPK2.class)
public class Car2 {
	@Id
	@Column(name = "MANUFACTURER")
	private String manufacturer;

	@Id
	@Column(name = "TYPE")
	private String type;

	@Id
	@Column(name = "SERIES")
	private String series;

	@Column(name = "MIN_PRICE")
	private BigDecimal minPrice;

	@Column(name = "PRICE")
	private BigDecimal price;

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
