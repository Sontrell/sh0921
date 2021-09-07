package svc.Services;

import org.junit.jupiter.api.Test;
import svc.Dtos.RentalInfo;

import static org.junit.jupiter.api.Assertions.*;

class ReturnDateServiceTest {
    private ReturnDateService returnDateService;

    @Test
    public void testCalculate_ValidRentalInfo_ReturnsCorrectDueDate() throws Exception {
        RentalInfo rentalInfo = RentalInfo.builder().rentalDays(2).checkoutDate("01/01/20").build();
        returnDateService = new ReturnDateService();
        String actualDueDate = returnDateService.calculate(rentalInfo);

        assertEquals("01/03/20", actualDueDate);
        assertEquals("01/01/20", rentalInfo.getCheckoutDate());
        assertNotNull(rentalInfo.getRentalDays());
    }

    @Test
    public void testCalculate_NullRentalInfo_ThrowsException() {
        RentalInfo rentalInfo = RentalInfo.builder().rentalDays(0).checkoutDate("01/01/20").build();
        assertThrows(Exception.class, () -> {
            returnDateService.calculate(rentalInfo);
        });
    }

    @Test
    public void testCalculate_ZeroRentalDays_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            returnDateService.calculate(null);
        });
    }
}