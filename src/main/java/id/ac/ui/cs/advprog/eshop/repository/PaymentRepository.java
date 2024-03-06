package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        validatePayment(payment.getId());
        paymentData.add(payment);
        return payment;
    }

    private void validatePayment(String id) {
        if (paymentData.stream().anyMatch(payment -> payment.getId().equals(id))) {
            throw new IllegalStateException("Payment with ID " + id + " already exists");
        }
    }

    public Payment findById(String id) {
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getId().equals(id)) {
                return savedPayment;
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentData);
    }
}
