package com.receiptprocessorchallenge.fetchrewards;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpClient;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FetchRewardsApplicationTests {

    @Autowired
    private MockMvc mockMvc; //To call the rest API provided by Spring framework


    @Test
    public void givenAValidReceipt_whenPostRequestToAdd_thenStatusIsOk() throws Exception{

        String mockReceipt = "{\"retailer\": \"Target\", \"purchaseDate\" : \"2022-01-02\", \"purchaseTime\" : \"13:13\", \"total\" : \"1.25\", \"items\" : [{\"shortDescription\" : \"Pepsi\", \"price\" : \"1.25\"}]}";

        mockMvc.perform(MockMvcRequestBuilders.post("/receipts/process").contentType(MediaType.APPLICATION_JSON)
                .content(mockReceipt).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void givenAnInvalidReceipt_whenPostRequestToAdd_thenStatusIsBadRequest() throws Exception{

        String mockReceipt = "{\"retailer\": \"\", \"purchaseDate\" : \"2022-01-02\", \"purchaseTime\" : \"13:13\", \"total\" : \"1.25\", \"items\" : [{\"shortDescription\" : \"Pepsi\", \"price\" : \"1.25\"}]}";

        mockMvc.perform(MockMvcRequestBuilders.post("/receipts/process").contentType(MediaType.APPLICATION_JSON)
                .content(mockReceipt).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void givenReceiptIdThatExists_whenGetRequestToFetchPoints_thenStatusIsOk() throws Exception{

        String mockReceipt = "{\"retailer\": \"Target\", \"purchaseDate\" : \"2022-01-02\", \"purchaseTime\" : \"13:13\", \"total\" : \"1.25\", \"items\" : [{\"shortDescription\" : \"Pepsi\", \"price\" : \"1.25\"}]}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/receipts/process").contentType(MediaType.APPLICATION_JSON)
                        .content(mockReceipt)).andReturn();

        String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(MockMvcRequestBuilders.get("/receipts/"+ id + "/points")).andExpect(status().isOk());
    }

    @Test
    public void givenReceiptIdThatDoesNotExists_whenGetRequestToFetchPoints_thenStatusIsNotFound() throws Exception{

        String id = "cd785202-a53b-46fb-86da-780bd8a31893";

        mockMvc.perform(MockMvcRequestBuilders.get("/receipts/"+ id + "/points")).andExpect(status().isNotFound());
    }

}
