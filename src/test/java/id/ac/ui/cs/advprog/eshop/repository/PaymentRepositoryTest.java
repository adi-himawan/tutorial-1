package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;

    List<Payment> payments;
    List<Product> products;
    List<Order> orders;
    Map<String, String> mapPaymentByVoucher;
    Map<String, String> mapPaymentByTransfer;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", 
            products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
            products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        mapPaymentByVoucher = new HashMap<>();
        mapPaymentByVoucher.put("voucherCode", "ESHOP1234ABC4321");
        Payment voucher = new Payment("e9ec8084-0c1a-4583-b2e5-c6374313b281", "VOUCHER", 
            order1, mapPaymentByVoucher, "PENDING");
        payments.add(voucher);

        Map<String, String> mapPaymentByTransfer = new HashMap<>();
        mapPaymentByTransfer.put("bankName", "ABC");
        mapPaymentByTransfer.put("referenceCode", "0101010101");
        Payment transfer = new Payment("f0e34503-65b6-48a9-b8a9-06234470c11f", "BANK", 
            order2, mapPaymentByTransfer, "PENDING");
        payments.add(transfer);
    }

    @Test
    void testAddPaymentSuccess() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payments.get(0).getId(), findResult.getId());
        assertSame(payments.get(0).getOrder(), findResult.getOrder());
        assertEquals(payments.get(0).getMethod(), findResult.getMethod());
        assertEquals(payments.get(0).getStatus(), findResult.getStatus());
        assertEquals(payments.get(0).getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(payments.size(), allPayments.size());
    }
}
