/*
 * POJO for the sub items of receipt
*/

package com.receiptprocessorchallenge.fetchrewards;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReceiptItemsInfo {

    @NotBlank
    @NotNull
    private String shortDescription;
    @NotBlank
    @NotNull
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
