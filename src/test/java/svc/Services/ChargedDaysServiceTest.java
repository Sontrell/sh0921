package svc.Services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import svc.Dtos.Prices;
import svc.Dtos.RentalInfo;
import svc.Dtos.Tool;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ChargedDaysServiceTest {
    private static ChargedDaysService chargedDaysService;
    private static RentalInfo rentalInfo;
    @BeforeAll
    public static void setUp() {
        chargedDaysService = new ChargedDaysService();
    }

    @Test
    public void testCalculateChargeDays_ValidRentalInfo_ExpectedChargedDaysReturned() throws ParseException {
        rentalInfo = RentalInfo.builder()
                .checkoutDate("2/01/20")
                .rentalDays(2)
                .toolInfo(Tool.builder().price(Prices.builder().holidayCharge(true).weekdayCharge(true).weekendCharge(true).build()).build())
                .build();
        assertEquals(2, chargedDaysService.calculateChargeDays(rentalInfo));
        //Could add mockito to verify method calls were made
    }

    @Test
    public void testCalculateChargeDays_ValidRentalInfo_HolidayRental_Charged_ExpectedChargedDaysReturned() throws ParseException {
        rentalInfo = RentalInfo.builder()
                .checkoutDate("8/5/21")
                .rentalDays(2)
                .toolInfo(Tool.builder().price(Prices.builder().holidayCharge(true).weekdayCharge(true).weekendCharge(true).build()).build())
                .build();
        assertEquals(2, chargedDaysService.calculateChargeDays(rentalInfo));
        //Could add mockito to verify method calls were made
    }

    @Test
    public void testCalculateChargeDays_ValidRentalInfo_HolidayRental_NotCharged_ExpectedChargedDaysReturned() throws ParseException {
        rentalInfo = RentalInfo.builder()
                .checkoutDate("8/5/21")
                .rentalDays(2)
                .toolInfo(Tool.builder().price(Prices.builder().holidayCharge(false).weekdayCharge(true).weekendCharge(true).build()).build())
                .build();
        assertEquals(1, chargedDaysService.calculateChargeDays(rentalInfo));
        //Could add mockito to verify method calls were made
    }

    @Test
    public void testCalculateChargeDays_ValidRentalInfo_WeekendRental_Charged_ExpectedChargedDaysReturned() throws ParseException {
        rentalInfo = RentalInfo.builder()
                .checkoutDate("9/11/21")
                .rentalDays(2)
                .toolInfo(Tool.builder().price(Prices.builder().holidayCharge(false).weekdayCharge(true).weekendCharge(true).build()).build())
                .build();
        assertEquals(2, chargedDaysService.calculateChargeDays(rentalInfo));
        //Could add mockito to verify method calls were made
    }

    @Test
    public void testCalculateChargeDays_ValidRentalInfo_WeekendRental_NotCharged_ExpectedChargedDaysReturned() throws ParseException {
        rentalInfo = RentalInfo.builder()
                .checkoutDate("9/11/21")
                .rentalDays(2)
                .toolInfo(Tool.builder().price(Prices.builder().holidayCharge(false).weekdayCharge(false).weekendCharge(false).build()).build())
                .build();
        assertEquals(0, chargedDaysService.calculateChargeDays(rentalInfo));
        //Could add mockito to verify method calls were made
    }

    @Test
    public void testCalculateChargeDays_NullCheckoutDate_ThrowsNullPointerException() throws ParseException {
        rentalInfo = RentalInfo.builder()
                .checkoutDate(null)
                .rentalDays(2)
                .toolInfo(Tool.builder().price(Prices.builder().holidayCharge(false).weekdayCharge(false).weekendCharge(false).build()).build())
                .build();
        assertThrows(NullPointerException.class, () -> {
            chargedDaysService.calculateChargeDays(rentalInfo);
        });
    }

    @Test
    public void testCalculateChargeDays_DefaultRentalDate_HolidayDate_NoChargeDayCalculated() throws ParseException {
        rentalInfo = RentalInfo.builder()
                .checkoutDate("8/5/21")
                .rentalDays(0)
                .toolInfo(Tool.builder().price(Prices.builder().holidayCharge(true).weekdayCharge(false).weekendCharge(false).build()).build())
                .build();
        assertEquals(0,chargedDaysService.calculateChargeDays(rentalInfo));
        //Could add mockito to verify method calls were made
    }
}