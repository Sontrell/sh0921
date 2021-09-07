package svc.Services;

import svc.Data.ToolsMap;
import svc.Dtos.Prices;
import svc.Dtos.RentalInfo;
import svc.Dtos.Tool;

/*
This Class starts the application for the POS by taken in arguments given by the main class on start up
It then validates the input size and the percent range
After it calls the necessary services to populate and calculate the fields to generate the completed RentalAgreement
There is also a check when generating the rental agreement to insure there a no null fields
 */
public class StartApplication {
    public void startApplication(String[] args) throws Exception {
        ReturnDateService returnDateService = new ReturnDateService();
        ChargedDaysService chargedDaysService = new ChargedDaysService();
        CheckoutTotalService checkoutTotalService = new CheckoutTotalService();
        GenerateRentalAgreementService generateRentalAgreementService = new GenerateRentalAgreementService();

        //Check to make sure the input was correct
        if (args.length != 4) throw new Exception("Too many or too few arguments were given");
        //Check to make sure the discount price is within range
        if(0>Integer.parseInt(args[2]) || Integer.parseInt(args[2])>100) throw new Exception("Discount Percent is out of Range");

        //Build new RentalInfo Object
        RentalInfo rentalInfo = RentalInfo.builder().build();
        rentalInfo.setToolInfo(Tool.builder().build());
        rentalInfo.getToolInfo().setPrice(Prices.builder().build());

        rentalInfo.getToolInfo().setCode(args[0].toUpperCase());
        rentalInfo.setRentalDays(Integer.parseInt(args[1]));
        rentalInfo.setDiscountPercent(Integer.parseInt(args[2]));
        rentalInfo.setCheckoutDate(args[3]);

        setToolInfo(rentalInfo, rentalInfo.getToolInfo().getCode());
        rentalInfo.setDueDate(returnDateService.calculate(rentalInfo));
        rentalInfo.setChargeDays(chargedDaysService.calculateChargeDays(rentalInfo));
        checkoutTotalService.calculateChargeTotal(rentalInfo);
        try {
            generateRentalAgreementService.generateAgreement(rentalInfo);
        } catch (NullPointerException e) {throw new Exception("There was a null value when generating the rental agreement");}
    }

    private static void setToolInfo(RentalInfo rentalInfo, String toolCode) {
        ToolsMap toolsMap = new ToolsMap();
        Tool toolInfo = toolsMap.toolsMap().get(toolCode);

        //mapping tool info
        rentalInfo.setToolInfo(toolInfo);
    }
}
