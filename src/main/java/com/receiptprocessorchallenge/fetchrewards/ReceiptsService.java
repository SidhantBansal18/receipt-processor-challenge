package com.receiptprocessorchallenge.fetchrewards;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ReceiptsService {

    HashMap<String, Integer> receiptsScore;
    private ReceiptInfo receipt;

    public ReceiptsService() {
        receiptsScore = new HashMap<>();
    }

    public PostResponse processReceipts (ReceiptInfo receipt){
        this.receipt = receipt;
        String currentUUID = generateUUID();
        int score = calculateTotalScore();
        receiptsScore.put(currentUUID, score);
        return new PostResponse(currentUUID);
    }

    public GetResponse getScore(String id){
        if(!receiptsScore.containsKey(id)){
            throw new NullPointerException("The receipt for the id " + id + " does not exist");
        }
        GetResponse getResponse = new GetResponse(receiptsScore.get(id));
        return getResponse;
    }

    public String generateUUID(){
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        return uuidString;
    }

    public int calculateTotalScore(){
        int totalScore = 0;

        totalScore += retailerNameScore();
        totalScore += receiptTotalAmountScore();
        totalScore += receiptItemsScore();
        totalScore += receiptPurchaseDateScore();
        totalScore += receiptPurchaseTimeScore();

        return totalScore;
    }

    public int retailerNameScore(){
        int currentScore = 0;
        String retailer = receipt.getRetailer().trim();
        for(char c: retailer.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                currentScore++;
            }
        }
        return currentScore;
    }

    public int receiptTotalAmountScore(){
        Double score = receipt.getTotal();
        int currentScore = 0;

        if(score % 1 == 0){
            currentScore += 50;
        }

        if(score % 0.25 == 0){
            currentScore += 25;
        }

        return currentScore;
    }

    public int receiptItemsScore() {
        List<ReceiptItemsInfo> currItems = receipt.getItems();
        int currentScore = 0;
        currentScore += 5 * (currItems.size() / 2);

        for(ReceiptItemsInfo item: currItems){
            if(item.getShortDescription().trim().length() % 3 == 0){
                int updatedPrice = (int) Math.ceil(item.getPrice() * 0.2);
                currentScore += updatedPrice;
            }
        }

        return currentScore;
    }

    public int receiptPurchaseDateScore(){
        String [] purchaseDate = receipt.getPurchaseDate().trim().split("-");
        int currentScore = 0;

        if(Integer.valueOf(purchaseDate[2]) % 2 != 0){
            currentScore += 6;
        }
        return currentScore;
    }

    public int receiptPurchaseTimeScore(){
        String [] purchaseTime = receipt.getPurchaseTime().trim().split(":");
        int currentScore = 0;

        if(Integer.valueOf(purchaseTime[0]) >= 14 && Integer.valueOf(purchaseTime[0]) < 16){
            currentScore += 10;
        }
        return currentScore;
    }
}
