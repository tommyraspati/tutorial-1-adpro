package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import enums.PaymentStatus;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab", "VOUCHER", this.paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("12345678-abcd-efgh-ijkl-1234567890ab", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());

        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab", "VOUCHER", this.paymentData, PaymentStatus.SUCCESS.getValue());

        assertEquals("12345678-abcd-efgh-ijkl-1234567890ab", payment.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals("VOUCHER", payment.getMethod());

        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows (IllegalArgumentException.class, () -> {
            Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab", "VOUCHER", this.paymentData, "INVALID");
        });
    }

    @Test
    void testSetPaymentDataEmpty() {
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab", "VOUCHER", this.paymentData);
        this.paymentData.clear();
        assertThrows (IllegalArgumentException.class, () -> {
            payment.setPaymentData(this.paymentData);
        });
    }

    @Test
    void testSetPaymentDataSuccess() {
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab", "VOUCHER", this.paymentData);
        this.paymentData.put("voucherCode", "ESHOPXXX");
        payment.setPaymentData(this.paymentData);

        assertSame(this.paymentData, payment.getPaymentData());
    }
}