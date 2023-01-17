package main;

public class HeaderTransaction {

	private String transactionID;
	private String transactionDate;
	
	public HeaderTransaction(String transactionID, String transactionDate) {
		super();
		this.transactionID = transactionID;
		this.transactionDate = transactionDate;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
}
