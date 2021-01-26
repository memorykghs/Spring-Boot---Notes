package com.springboot.rest.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class CarPK2 {

	@Id
	@Column(name = "MANUFACTURER")
	private String manufacturer;

	@Id
	@Column(name = "TYPE")
	private String type;

	@Id
	@Column(name = "SERIES")
	private String series;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.manufacturer.hashCode();
		hash = hash * prime + this.type.hashCode();
		hash = hash * prime + this.series.hashCode();
		
		return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (!(other instanceof CarPK2)) {
			return false;
		}

		CarPK2 castOther = (CarPK2) other;

		return this.manufacturer.equals(castOther) && this.type.equals(castOther.type)
				&& this.series.equals(castOther.series);
	}
}
