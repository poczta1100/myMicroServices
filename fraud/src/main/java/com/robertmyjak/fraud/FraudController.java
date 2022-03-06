package com.robertmyjak.fraud;

import com.robertmyjak.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudCustomer(@PathVariable Integer customerId) {

        boolean fraud = fraudCheckService.isFraud(customerId);
        log.info("fraud check for customer {}", customerId);

        return new FraudCheckResponse(fraud);
    }


}
