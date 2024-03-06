package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentByTransfer extends Payment {
    public PaymentByTransfer(String id, Order order, String method, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }

    public PaymentByTransfer(String id, Order order, String method, Map<String, String> paymentData) {
        super(id, method, order, paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.get("bankName").isBlank() || paymentData.get("referenceCode").isBlank()) {
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }
    }
}
