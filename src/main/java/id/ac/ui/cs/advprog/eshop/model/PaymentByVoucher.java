package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentByVoucher extends Payment {
    public PaymentByVoucher(String id, Order order, String method, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }

    public PaymentByVoucher(String id, Order order, String method, Map<String, String> paymentData) {
        super(id, method, order, paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        int charCount = 0;
        int numCount = 0;
        String voucherCode = paymentData.get("voucherCode");

        for (int i = 0; i < voucherCode.length(); i++) {
            if (Character.isDigit(voucherCode.charAt(i))) {
                numCount += 1;
            }
            charCount++;
        }

        if (charCount != 16 || !voucherCode.startsWith("ESHOP") || numCount != 8) {
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }
    }
}
