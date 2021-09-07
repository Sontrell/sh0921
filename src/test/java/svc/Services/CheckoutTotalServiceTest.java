package svc.Services;

import org.junit.jupiter.api.Test;
import svc.Dtos.Prices;
import svc.Dtos.RentalInfo;
import svc.Dtos.Tool;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTotalServiceTest {
    private CheckoutTotalService checkoutTotalService;

    @Test
    public void testCalculateChargeTotal_ValidRentalInfo_CalculatesCorrectly() {
        checkoutTotalService = new CheckoutTotalService();

        RentalInfo rentalInfo = RentalInfo.builder().chargeDays(1).discountPercent(10)
                .toolInfo(Tool.builder().price(Prices.builder().dailyPrice(1.00).build()).build()).build();
        checkoutTotalService.calculateChargeTotal(rentalInfo);
        assertEquals(1,rentalInfo.getChargeDays());
        assertEquals(10,rentalInfo.getDiscountPercent());
        assertEquals(.10, rentalInfo.getDiscountAmount());
        assertEquals(.90, rentalInfo.getPostTotal());
        assertEquals(1.00, rentalInfo.getPreTotal());
    }

    @Test
    public void testCalculateChargeTotal_DefaultRentalInfo_CalculatesCorrectlyToZero() {
        checkoutTotalService = new CheckoutTotalService();

        RentalInfo rentalInfo = RentalInfo.builder()
                .toolInfo(Tool.builder().price(Prices.builder().build()).build()).build();
        checkoutTotalService.calculateChargeTotal(rentalInfo);
        assertEquals(0,rentalInfo.getChargeDays());
        assertEquals(0,rentalInfo.getDiscountPercent());
        assertEquals(0, rentalInfo.getDiscountAmount());
        assertEquals(0, rentalInfo.getPostTotal());
        assertEquals(0, rentalInfo.getPreTotal());
    }

}