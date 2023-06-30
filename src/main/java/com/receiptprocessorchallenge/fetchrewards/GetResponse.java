/*
 * POJO for the get response when a receipt id that exists is sent.
*/

package com.receiptprocessorchallenge.fetchrewards;

public class GetResponse {

    private int points;

    public GetResponse(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
