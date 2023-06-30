package com.receiptprocessorchallenge.fetchrewards;

public class ReceiptItemsInfo {

    private String shortDescription;
    private Double price;

    public ReceiptItemsInfo(String shortDescription, Double price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
