package com.springboot.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MAGICIAN")
public class MagicianEntity {
	@Id
	@Column(name = "MAGICIAN_NAME")
	private String magicianName;
	
	@Column(name = "LEVEL")
	private int level;
	
	@Column(name = "TYPE")
	private String type;

	public String getMagicianName() {
		return magicianName;
	}

	public void setMagicianName(String magicianName) {
		this.magicianName = magicianName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
