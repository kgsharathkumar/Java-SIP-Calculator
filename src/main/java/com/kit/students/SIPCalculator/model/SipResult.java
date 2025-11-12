package com.kit.students.SIPCalculator.model;

/**
 * Model class to hold SIP calculation results.
 */
public class SipResult {

    private double investedAmount;
    private double totalReturns;
    private double maturityValue;

    // ✅ Default no-arg constructor (required for JSON serialization)
    public SipResult() {
    }

    // ✅ Parameterized constructor (for result creation)
    public SipResult(double investedAmount, double totalReturns, double maturityValue) {
        this.investedAmount = investedAmount;
        this.totalReturns = totalReturns;
        this.maturityValue = maturityValue;
    }

    // ✅ Getters and setters (Spring/JSON needs these)
    public double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(double investedAmount) {
        this.investedAmount = investedAmount;
    }

    public double getTotalReturns() {
        return totalReturns;
    }

    public void setTotalReturns(double totalReturns) {
        this.totalReturns = totalReturns;
    }

    public double getMaturityValue() {
        return maturityValue;
    }

    public void setMaturityValue(double maturityValue) {
        this.maturityValue = maturityValue;
    }

    @Override
    public String toString() {
        return "SipResult{" +
                "investedAmount=" + investedAmount +
                ", totalReturns=" + totalReturns +
                ", maturityValue=" + maturityValue +
                '}';
    }
}
