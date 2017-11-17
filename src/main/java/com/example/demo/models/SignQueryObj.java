package com.example.demo.models;

public class SignQueryObj {
	private String status;
	private String signId;
	private String idName;
	private String idCard;
	private String phone;
	private String bankAccount;
	private String bankName;
	private String bankCode;
	
	public SignQueryObj() {
		
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
//	@Override
//	public String toString() {
//		return "SignQuery{" +
//				"status=" + status + ",signId=" + signId + ",idName=" + idName + ",idCard=" + idCard + ",phone=" + phone + ",bankAccount=" + bankAccount
//				+ ",bankName=" + bankName + ",bankCode=" + bankCode
//				+ "}";
//	}
}
