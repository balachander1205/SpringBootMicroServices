package com.ebank.eBanking.model;

public class FundTransferModel {
	private int accountno;
	private int payeeAccNo;
	private float amount;
	
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public int getPayeeAccNo() {
		return payeeAccNo;
	}
	public void setPayeeAccNo(int payeeAccNo) {
		this.payeeAccNo = payeeAccNo;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	private String comment;
}
