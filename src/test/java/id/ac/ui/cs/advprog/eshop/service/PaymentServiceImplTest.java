package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.*;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Product> products;
    List<Order> orders;
    Map<String, String> mapPaymentByVoucher;
    Map<String, String> mapPaymentByTransfer;

    @BeforeEach
    void setUp() {
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

        payments = new ArrayList<>();
        
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
    void testAddPayment() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
    }

    @Test
    void testSetStatus() {
        Payment payment = payments.get(0);

        assertEquals("PENDING", payment.getStatus());
        paymentService.setStatus(payment, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payments.get(0), "MEOW");
        });
    }

    @Test
    void testUpdateOrderStatusIfPaymentStatusSuccess() {
        Payment payment = payments.get(1);

        paymentService.setStatus(payment, "SUCCESS");
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }

    @Test
    void testUpdateOrderStatusIfPaymentStatusRejected() {
        Payment payment = payments.get(1);

        paymentService.setStatus(payment, "REJECTED");
        assertEquals("FAILED", payment.getOrder().getStatus());
    }

    @Test
    void testUpdateOrderStatusIfPaymentStatusInvalid() {
        Payment payment = payments.get(1);

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payment, "MEOW");
        });
        assertEquals("PENDING", payment.getStatus());
        assertEquals("WAITING_PAYMENT", payment.getOrder().getStatus());
    }

    @Test
    void testGetPaymentIfIdFound() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment findResult = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("zczc");

        Payment payment = paymentService.getPayment("zczc");
        assertNull(payment);
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayments();

        List<Payment> allPayments = paymentService.getAllPayments();
        assertTrue(allPayments.containsAll(payments));
    }
}
