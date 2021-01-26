package com.springboot.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADVENTURER")
public class AdventurerEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ADVENTURER_ID")
	private int adventurerID;
	
	@Column(name = "ADVENTURER_NAME")
	private String adventurerName;
	
	@Column(name = "GUILD_NAME")
	private String guildNameString;

	public int getAdventurerID() {
		return adventurerID;
	}

	public void setAdventurerID(int adventurerID) {
		this.adventurerID = adventurerID;
	}

	public String getAdventurerName() {
		return adventurerName;
	}

	public void setAdventurerName(String adventurerName) {
		this.adventurerName = adventurerName;
	}

	public String getGuildNameString() {
		return guildNameString;
	}

	public void setGuildNameString(String guildNameString) {
		this.guildNameString = guildNameString;
	}
}
