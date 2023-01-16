package main;

public class CheckOut {
	
	private Integer COproductID;
	private String COuserID;
	private String COproductName;
	private String COproductType;
	private Integer COquantity;
	private Integer COproductPrice;
	
	public CheckOut(Integer cOproductID, String cOuserID, String cOproductName, String cOproductType,
			Integer cOquantity, Integer cOproductPrice) {
		super();
		COproductID = cOproductID;
		COuserID = cOuserID;
		COproductName = cOproductName;
		COproductType = cOproductType;
		COquantity = cOquantity;
		COproductPrice = cOproductPrice;
	}

	public Integer getCOproductID() {
		return COproductID;
	}

	public void setCOproductID(Integer cOproductID) {
		COproductID = cOproductID;
	}

	public String getCOuserID() {
		return COuserID;
	}

	public void setCOuserID(String cOuserID) {
		COuserID = cOuserID;
	}

	public String getCOproductName() {
		return COproductName;
	}

	public void setCOproductName(String cOproductName) {
		COproductName = cOproductName;
	}

	public String getCOproductType() {
		return COproductType;
	}

	public void setCOproductType(String cOproductType) {
		COproductType = cOproductType;
	}

	public Integer getCOquantity() {
		return COquantity;
	}

	public void setCOquantity(Integer cOquantity) {
		COquantity = cOquantity;
	}

	public Integer getCOproductPrice() {
		return COproductPrice;
	}

	public void setCOproductPrice(Integer cOproductPrice) {
		COproductPrice = cOproductPrice;
	}
	

	
	
	

}
