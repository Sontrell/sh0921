package svc;

import svc.Data.ToolsMap;
import svc.Dtos.Prices;
import svc.Dtos.RentalInfo;
import svc.Dtos.Tool;
import svc.Services.CheckoutTotalService;
import svc.Services.ChargedDaysService;
import svc.Services.ReturnDateService;

public class PointOfSaleMain {
    public static void main(String[] args) throws Exception {
        ToolsMap map = new ToolsMap();
        System.out.println(map.toolsMap());
        ReturnDateService returnDateService = new ReturnDateService();
        ChargedDaysService chargedDaysService = new ChargedDaysService();
        CheckoutTotalService checkoutTotalService = new CheckoutTotalService();
        Prices priceData = Prices.builder().build();
        RentalInfo rentalInfo = RentalInfo.builder().build();
        Tool toolInfo = Tool.builder().build();

        priceData.setWeekdayCharge(true);
        priceData.setHolidayCharge(false);
        priceData.setWeekendCharge(false);
        priceData.setDailyPrice(1.99);


        rentalInfo.setRentalDays(2);
        rentalInfo.setCheckoutDate("9/11/21");
        rentalInfo.setToolInfo(toolInfo);
        rentalInfo.setDiscountPercent(10);
        rentalInfo.getToolInfo().setPrice(priceData);
        rentalInfo.setChargeDays(chargedDaysService.calculateChargeDays(rentalInfo));
        checkoutTotalService.calculateChargeTotal(rentalInfo);
        System.out.println(rentalInfo);
    }
}
