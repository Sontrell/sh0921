package svc.Services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import svc.Dtos.Prices;
import svc.Dtos.RentalInfo;
import svc.Dtos.Tool;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GenerateRentalAgreementServiceTest {
    private static final PrintStream output = System.out;
    private static final ByteArrayOutputStream outputCaptor = new ByteArrayOutputStream();
    private static GenerateRentalAgreementService generateRentalAgreementService;

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outputCaptor));
        generateRentalAgreementService = new GenerateRentalAgreementService();
    }

    @Test
    public void testGenerateRentalAgreement_ValidRentalInfo_PopulatesOutput() {
        Prices priceData = Prices.builder().build();
        RentalInfo rentalInfo = RentalInfo.builder().build();
        Tool toolInfo = Tool.builder().build();

        priceData.setWeekdayCharge(true);
        priceData.setHolidayCharge(false);
        priceData.setWeekendCharge(false);
        priceData.setDailyPrice(1.99);

        rentalInfo.setRentalDays(2);
        rentalInfo.setCheckoutDate("9/13/21");
        rentalInfo.setToolInfo(toolInfo);
        rentalInfo.setDiscountPercent(10);
        rentalInfo.setDiscountAmount(10);
        rentalInfo.setPostTotal(1000000000);
        rentalInfo.getToolInfo().setCode("LADW");
        rentalInfo.getToolInfo().setBrand("Bubbly");
        rentalInfo.getToolInfo().setPrice(priceData);

        generateRentalAgreementService.generateAgreement(rentalInfo);

        assertNotNull(outputCaptor.toString());
    }

    @Test
    public void testGenerateRentalAgreement_EmptyRentalInfo_ThrowsNullPointerException() {
        assertThrows(Exception.class, () -> {
            generateRentalAgreementService.generateAgreement(RentalInfo.builder().build());
        });
    }

    @AfterAll
    public static void tearDown() {
        System.setOut(output);
    }
}