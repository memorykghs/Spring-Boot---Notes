package com.springboot.rest.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StoreRequest {
	@JsonProperty(value = "STROE_ID")
	private Integer storeID;

	@JsonProperty(value = "STORE_NAME")
	private String storeName;

	@JsonProperty(value = "OWNER")
	private String owner;

	@JsonProperty(value = "TEL")
	private String tel;

	@JsonProperty(value = "FAX")
	private String fax;

	@JsonProperty(value = "MOBILE")
	private String mobile;

	@JsonProperty(value = "ADDRESS")
	private String address;

	@JsonProperty(value = "EVALUATION")
	private char evaluation;

	@JsonProperty(value = "CREATE_USER")
	private String createUser;

	@JsonProperty(value = "CREATE_DATE")
	private Date createDate;

	public Integer getStoreID() {
		return storeID;
	}

	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(char evaluation) {
		this.evaluation = evaluation;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
