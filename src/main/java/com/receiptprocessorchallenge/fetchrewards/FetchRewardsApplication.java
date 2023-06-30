package com.receiptprocessorchallenge.fetchrewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FetchRewardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FetchRewardsApplication.class, args);
    }

}
