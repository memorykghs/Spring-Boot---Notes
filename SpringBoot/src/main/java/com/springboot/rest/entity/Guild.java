package com.springboot.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GUILD")
public class Guild implements Serializable{

	private static final long serialVersionUID = 1L;
	
//	@OneToMany(targetEntity = AdventurerEntity.class)
//	@JoinColumn(name = "GUILD_NAME")
//	private List<AdventurerEntity> adventurerList;
	
	@Id
	@Column(name = "GUILD_ID")
	private int guildID;
	
	@Column(name = "GUILD_NAME")
	private String guildName;
	
	@Column(name = "GUILD_LEVEL")
	private int guildLevel;

	public int getGuildID() {
		return guildID;
	}

	public void setGuildID(int guildID) {
		this.guildID = guildID;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public int getGuildLevel() {
		return guildLevel;
	}

	public void setGuildLevel(int guildLevel) {
		this.guildLevel = guildLevel;
	}

}
