package com.receiptprocessorchallenge.fetchrewards;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(receiptsService.processReceipts(receipt));
    }

    @GetMapping("/{id}/points")
    @ResponseBody
    public ResponseEntity<GetResponse> addReceipt(@PathVariable("id") String id){
        return ResponseEntity.ok(receiptsService.getScore(id));
    }
}
