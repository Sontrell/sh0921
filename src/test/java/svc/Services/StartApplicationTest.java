package svc.Services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import svc.Dtos.Prices;
import svc.Dtos.RentalInfo;
import svc.Dtos.Tool;

import static org.junit.jupiter.api.Assertions.assertThrows;

/*
Test class to test the functionally of the program
no assertions were made and will be verified manually with the system output.
 */
class StartApplicationTest {
    public static StartApplication startApplication;
    public static RentalInfo rentalInfo;

    @BeforeAll
    public static void setUp() {
        startApplication = new StartApplication();
        rentalInfo = RentalInfo.builder().build();
        rentalInfo.setToolInfo(Tool.builder().build());
        rentalInfo.getToolInfo().setPrice(Prices.builder().build());
    }

    @Test
    public void testCase1() {
        String[] args = new String[4];
        args[0] = "JAKR";
        args[1] = "5";
        args[2] = "101";
        args[3] = "9/3/15";

        assertThrows(Exception.class, () -> {
            startApplication.startApplication(args);
        });
    }

    @Test
    public void testCase2() throws Exception {
        String[] args = new String[4];
        args[0] = "LADW";
        args[1] = "3";
        args[2] = "10";
        args[3] = "7/2/20";

        System.out.println("GENERATING NEW RENTAL AGREEMENT FOR TEST CASE 2");
        startApplication.startApplication(args);
    }

    @Test
    public void testCase3() throws Exception {
        String[] args = new String[4];
        args[0] = "CHNS";
        args[1] = "5";
        args[2] = "25";
        args[3] = "7/2/15";

        System.out.println("GENERATING NEW RENTAL AGREEMENT FOR TEST CASE 3");
        startApplication.startApplication(args);
    }

    @Test
    public void testCase4() throws Exception {
        String[] args = new String[4];
        args[0] = "JAKD";
        args[1] = "6";
        args[2] = "0";
        args[3] = "9/3/15";

        System.out.println("GENERATING NEW RENTAL AGREEMENT FOR TEST CASE 4");
        startApplication.startApplication(args);
    }

    @Test
    public void testCase5() throws Exception {
        String[] args = new String[4];
        args[0] = "JAKR";
        args[1] = "9";
        args[2] = "0";
        args[3] = "7/2/15";

        System.out.println("GENERATING NEW RENTAL AGREEMENT FOR TEST CASE 5");
        startApplication.startApplication(args);
    }

    @Test
    public void testCase6() throws Exception {
        String[] args = new String[4];
        args[0] = "JAKR";
        args[1] = "4";
        args[2] = "50";
        args[3] = "7/2/20";

        System.out.println("GENERATING NEW RENTAL AGREEMENT FOR TEST CASE 6");
        startApplication.startApplication(args);
    }
}