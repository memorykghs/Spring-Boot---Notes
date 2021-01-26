package com.springboot.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.data.annotation.Id;

@Embeddable
public class GuildMemberPKEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "GUILD_ID")
	private String guildID;

	@Id
	@Column(name = "MEMBER_ID")
	private int memberID;


	public String getGuildID() {
		return guildID;
	}

	public void setGuildID(String guildID) {
		this.guildID = guildID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	/**
	 * equals方法
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;

		if (!(other instanceof GuildMemberPKEntity)) {
			return false;
		}

		GuildMemberPKEntity guildMemberPKEntityuildMemberPKEntity = (GuildMemberPKEntity) other;
		return this.guildID.equals(guildMemberPKEntityuildMemberPKEntity.getGuildID()) && this.memberID == guildMemberPKEntityuildMemberPKEntity.getMemberID();
	}

	/**
	 * hashcode方法
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.guildID.hashCode();
		hash = hash * prime + this.memberID;

		return hash;
	}
}
