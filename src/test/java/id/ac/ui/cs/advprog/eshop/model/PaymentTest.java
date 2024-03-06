package id.ac.ui.cs.advprog.eshop.model;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab", PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);

        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertEquals("12345678-abcd-efgh-ijkl-1234567890ab", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());

        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab",
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData, PaymentStatus.SUCCESS.getValue());
        assertEquals("12345678-abcd-efgh-ijkl-1234567890ab", payment.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab",
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("12345678-abcd-efgh-ijkl-1234567890ab", payment.getId());
        assertSame(this.paymentData, payment.getPaymentData());

    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows (IllegalArgumentException.class, () -> {
            Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab",
                    PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData, "INVALID");
        });
    }

    @Test
    void testSetPaymentDataEmpty() {
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab",
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        this.paymentData.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(this.paymentData);
        });
    }

    @Test
    void testSetPaymentDataSuccess() {
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab",
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        this.paymentData.put("voucherCode", "ESHOPXXX");
        payment.setPaymentData(this.paymentData);

        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentVoucherCodeMethod() {
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab", PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        assertEquals("12345678-abcd-efgh-ijkl-1234567890ab", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentBankTransferMethod() {
        Payment payment = new Payment("12345678-abcd-efgh-ijkl-1234567890ab", PaymentMethod.BANK_TRANSFER.getValue(), this.paymentData);
        assertEquals("12345678-abcd-efgh-ijkl-1234567890ab", payment.getId());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }
}