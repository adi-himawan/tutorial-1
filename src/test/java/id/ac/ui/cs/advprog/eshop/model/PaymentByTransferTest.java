package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PaymentByTransferTest {
    Map<String, String> paymentData;
    List<Payment> payments;
    List<Product> products;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
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
    }

    @AfterEach
    void cleanup() {
        paymentData.clear();
    }

    @Test
    void testCreatePaymentByTransferSuccessStatus() {
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "0101010101");

        Payment payment = new PaymentByTransfer("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            orders.get(1), "BANK", paymentData, "SUCCESS");

        assertSame(orders.get(1), payment.getOrder());
        assertEquals("2a61d0e1-6746-4a18-a4cd-85412d769edb", payment.getId());
        assertEquals("BANK", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentByTransferFailedEmptyBankName() {
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "0101010101");

        assertThrows(IllegalArgumentException.class, ()-> { 
            new PaymentByTransfer("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
                orders.get(1), "BANK", paymentData);
        });
    }

    @Test
    void testCreatePaymentByTransferFailedEmptyReferenceCode() {
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "");

        assertThrows(IllegalArgumentException.class, ()-> { 
            new PaymentByTransfer("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
                orders.get(1), "BANK", paymentData);
        });
    }
}
