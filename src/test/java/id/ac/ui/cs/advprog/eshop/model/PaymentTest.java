package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class PaymentTest {
    Map<String, String> paymentData;
    List<Product> products;
    Order order;

    @BeforeEach
    void setup() {
        this.paymentData = new HashMap<>();
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
            this.products, 1708560000L, "Safira Sudrajat");
    }

    @AfterEach
    void cleanup() {
        this.paymentData.clear();
    }

    @Test
    void testCreatePaymentWithoutOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
                "", null, paymentData);
        });
    }

    @Test
    void testCreatePaymentByVoucherDefaultStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC4321");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData);

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("2a61d0e1-6746-4a18-a4cd-85412d769edb", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testCreatePaymentByVoucherSuccessStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC4321");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData, "SUCCESS");

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("2a61d0e1-6746-4a18-a4cd-85412d769edb", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentByVoucherRejectedStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC4321");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData, "REJECTED");

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("2a61d0e1-6746-4a18-a4cd-85412d769edb", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentByVoucherInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC4321");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
                "", order, paymentData, "MEOW");
        });
    }

    @Test
    void testCreatePaymentByTransferDefaultStatus() {
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "0101010101");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData);

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("2a61d0e1-6746-4a18-a4cd-85412d769edb", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testCreatePaymentByTransferSuccessStatus() {
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "0101010101");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData, "SUCCESS");
        
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("2a61d0e1-6746-4a18-a4cd-85412d769edb", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentByTransferRejectedStatus() {
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "0101010101");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData, "REJECTED");

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("2a61d0e1-6746-4a18-a4cd-85412d769edb", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentByTransferInvalidStatus() {
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "0101010101");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
                "", order, paymentData, "MEOW");
        });
    }

    @Test
    void testSetPaymentByVoucherStatusToSuccess() {
        paymentData.put("voucherCode", "ESHOP1234ABC4321");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetPaymentByVoucherStatusToRejected() {
        paymentData.put("voucherCode", "ESHOP1234ABC4321");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData);

        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetPaymentByVoucherStatusToInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC4321");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }

    @Test
    void testSetPaymentByTransferStatusToSuccess() {
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "0101010101");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetPaymentByTransferStatusToRejected() {
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "0101010101");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData);

        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetPaymentByTransferStatusToInvalidStatus() {
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "0101010101");

        Payment payment = new Payment("2a61d0e1-6746-4a18-a4cd-85412d769edb", 
            "", order, paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }
}
