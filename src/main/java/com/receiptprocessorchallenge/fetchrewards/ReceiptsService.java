/*
 * Service class with the business logic of the code
*/

package com.receiptprocessorchallenge.fetchrewards;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ReceiptsService {

    HashMap<String, Integer> receiptsScore; //A hashmap to store valid receipts id and their scores
    private ReceiptInfo receipt;

    public ReceiptsService() {
        receiptsScore = new HashMap<>();
    }

    /*
    * This will take the valid receipt from the controller and assign it to a global receipt so that other methods can use it to.
    * It will calculate the corresponding score and store it in the hash map.
    * Returns the UUID that has been generated for the receipt.
    */
    public PostResponse processReceipts (ReceiptInfo receipt){
        this.receipt = receipt;
        String currentUUID = generateUUID();
        int score = calculateTotalScore();
        receiptsScore.put(currentUUID, score);
        return new PostResponse(currentUUID);
    }

    /*
    * Method to get score from the receipt id.
    */
    public GetResponse getScore(String id){
        GetResponse getResponse = new GetResponse(receiptsScore.get(id));
        return getResponse;
    }

    /*
     * Method to generate a UUID.
     */
    public String generateUUID(){
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        return uuidString;
    }

    /*
     * Method to calculate the total score of the corresponding receipt.
     */
    public int calculateTotalScore(){
        int totalScore = 0;

        totalScore += retailerNameScore();
        totalScore += receiptTotalAmountScore();
        totalScore += receiptItemsScore();
        totalScore += receiptPurchaseDateScore();
        totalScore += receiptPurchaseTimeScore();

        return totalScore;
    }

    //One point for every alphanumeric character in the retailer name.
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

    /*
     * 50 points if the total is a round dollar amount with no cents.
     * 25 points if the total is a multiple of 0.25.
     */
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

    /*
    * 5 points for every two items on the receipt.
    * If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer and add that to the score.
    * */
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

    //6 points if the day in the purchase date is odd.
    public int receiptPurchaseDateScore(){
        String [] purchaseDate = receipt.getPurchaseDate().trim().split("-");
        int currentScore = 0;

        if(Integer.valueOf(purchaseDate[2]) % 2 != 0){
            currentScore += 6;
        }
        return currentScore;
    }

    //10 points if the time of purchase is after 2:00pm and before 4:00pm.
    public int receiptPurchaseTimeScore(){
        String [] purchaseTime = receipt.getPurchaseTime().trim().split(":");
        int currentScore = 0;

        if(Integer.valueOf(purchaseTime[0]) >= 14 && Integer.valueOf(purchaseTime[0]) < 16){
            currentScore += 10;
        }
        return currentScore;
    }
}
