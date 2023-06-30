package com.receiptprocessorchallenge.fetchrewards;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/receipts")
public class ReceiptsController {

    private final ReceiptsService receiptsService;

    public ReceiptsController(ReceiptsService receiptsService) {
        this.receiptsService = receiptsService;
    }

    @PostMapping("/process")
    @ResponseBody
    public ResponseEntity<PostResponse> addReceipt(@RequestBody ReceiptInfo receipt){
        try {
            return ResponseEntity.ok(receiptsService.processReceipts(receipt));
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The receipt is invalid");
        }
    }

    @GetMapping("/{id}/points")
    @ResponseBody
    public ResponseEntity<GetResponse> addReceipt(@PathVariable("id") String id){
        try {
            return ResponseEntity.ok(receiptsService.getScore(id));
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No receipt found for that id");
        }
    }
}
