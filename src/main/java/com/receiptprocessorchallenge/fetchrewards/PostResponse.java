/*
 * POJO for the post response when a valid receipt is sent.
*/
package com.receiptprocessorchallenge.fetchrewards;

public class PostResponse {

    private String id;

    public PostResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
