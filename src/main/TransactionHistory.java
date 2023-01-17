package main;

public class TransactionHistory {
 
 private String THtransactionID;
 private String THproductID;
 private Integer THquantityID;
 
 public TransactionHistory(String tHtransactionID, String tHproductID, Integer tHquantityID) {
  super();
  THtransactionID = tHtransactionID;
  THproductID = tHproductID;
  THquantityID = tHquantityID;
 }
 public String getTHtransactionID() {
  return THtransactionID;
 }
 public void setTHtransactionID(String tHtransactionID) {
  THtransactionID = tHtransactionID;
 }
 public String getTHproductID() {
  return THproductID;
 }
 public void setTHproductID(String tHproductID) {
  THproductID = tHproductID;
 }
 public Integer getTHquantityID() {
  return THquantityID;
 }
 public void setTHquantityID(Integer tHquantityID) {
  THquantityID = tHquantityID;
 }
 
 

}