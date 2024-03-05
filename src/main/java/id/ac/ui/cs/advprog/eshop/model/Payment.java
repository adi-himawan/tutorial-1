package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import enums.PaymentStatus;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.setStatus(status);
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this(id, method, order, paymentData, "PENDING");
    }

    public void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException();
        } else {
            this.order = order;
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData == null) {
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
