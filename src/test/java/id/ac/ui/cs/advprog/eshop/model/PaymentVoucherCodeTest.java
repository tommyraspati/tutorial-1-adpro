package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentVoucherCodeTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testSetPaymentDataWithEmptyPaymentData() {
        PaymentVoucherCode payment = new PaymentVoucherCode("12345678-abcd-efgh-ijkl-1234567890ab", PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(this.paymentData));
    }

    @Test
    void testSetPaymentDataWithValidVoucherCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        PaymentVoucherCode payment = new PaymentVoucherCode("12345678-abcd-efgh-ijkl-1234567890ab", PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidVoucherCodeWithShortLength() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC567");
        PaymentVoucherCode payment = new PaymentVoucherCode("12345678-abcd-efgh-ijkl-1234567890ab", PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidVoucherCodeWithoutEshop() {
        this.paymentData.put("voucherCode", "1234ABC5678");
        PaymentVoucherCode payment = new PaymentVoucherCode("12345678-abcd-efgh-ijkl-1234567890ab", PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidVoucherCodeWithoutEightNumericalCharacters() {
        this.paymentData.put("voucherCode", "ESHOPABCDEFGH");
        PaymentVoucherCode payment = new PaymentVoucherCode("12345678-abcd-efgh-ijkl-1234567890ab", PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}