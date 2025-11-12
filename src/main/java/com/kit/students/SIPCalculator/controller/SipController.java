package com.kit.students.SIPCalculator.controller;

import com.kit.students.SIPCalculator.model.SipResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sip")
@CrossOrigin("*") // allow frontend calls
public class SipController {

    // ---- Main SIP Calculation Endpoint ----
    @GetMapping("/calculate")
    public SipResult calculateSip(
            @RequestParam(required = false, defaultValue = "0") double monthlyAmount,
            @RequestParam(required = false, defaultValue = "0") double annualRate,
            @RequestParam(required = false, defaultValue = "0") int years
    ) {
        // ✅ Basic input validation
        if (monthlyAmount <= 0 || annualRate <= 0 || years <= 0) {
            return new SipResult(0, 0, 0);
        }

        // Convert annual interest rate to monthly rate
        double monthlyRate = (annualRate / 12) / 100;
        int months = years * 12;

        // SIP formula:
        // FV = P * [( (1 + r)^n - 1 ) / r ] * (1 + r)
        double futureValue = monthlyAmount * (Math.pow(1 + monthlyRate, months) - 1) / monthlyRate * (1 + monthlyRate);

        // Total invested
        double investedAmount = monthlyAmount * months;

        // Profit / Returns
        double totalReturns = futureValue - investedAmount;

        // Return result
        return new SipResult(investedAmount, totalReturns, futureValue);
    }

    // ---- Handle Missing Request Parameters Gracefully ----
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        return "⚠️ Missing required parameter: " + ex.getParameterName()
                + ". Please provide all parameters: monthlyAmount, annualRate, and years.";
    }
}
