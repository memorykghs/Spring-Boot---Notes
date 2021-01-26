package com.springboot.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GUILD_MEMBER")
public class GuildMember implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "GUILD_ID")
	private int guildID;
	
	@Id
	@Column(name = "MEMBER_ID")
	private int memberID;
	
	@Column(name = "ADVENTURER_ID")
	private int AdventurerID;

}
