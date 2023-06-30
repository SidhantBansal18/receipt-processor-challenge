package com.receiptprocessorchallenge.fetchrewards;

import java.util.List;

public class ReceiptInfo {

    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private Double total;
    private List<ReceiptItemsInfo> items;

    public ReceiptInfo(String retailer, String purchaseDate, String purchaseTime, Double total, List<ReceiptItemsInfo> items) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.total = total;
        this.items = items;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ReceiptItemsInfo> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItemsInfo> items) {
        this.items = items;
    }
}
