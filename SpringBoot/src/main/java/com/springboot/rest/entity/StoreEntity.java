package com.springboot.rest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_STORE")
public class StoreEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STROE_ID")
	private Integer storeID;

	@Column(name = "STORE_NAME")
	private String storeName;

	@Column(name = "OWNER")
	private String owner;

	@Column(name = "TEL")
	private String tel;

	@Column(name = "FAX")
	private String fax;

	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "EVALUATION")
	private char evaluation;

	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Column(name = "CREATE_DATE")
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
