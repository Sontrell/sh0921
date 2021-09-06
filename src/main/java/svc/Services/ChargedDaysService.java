package svc.Services;

import svc.Dtos.Prices;
import svc.Dtos.RentalInfo;
import svc.utils.DateFormatterUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class ChargedDaysService {
    private DateFormatterUtil dateFormatterUtil = new DateFormatterUtil();
    private Calendar calendar = Calendar.getInstance();

    public int calculateChargeDays(RentalInfo rentalInfo) throws ParseException {
        int unchargedDays = 0;
        Prices priceData = rentalInfo.getToolInfo().getPrice();

        if(!priceData.isHolidayCharge()){
            unchargedDays += countHolidays(rentalInfo.getCheckoutDate());
        } if (!priceData.isWeekdayCharge()){
            //All options are set to true right now coding for the potential of being false at some point
            //Add a subtraction for any holidays counted and assign to new field
            unchargedDays += countWeekdays(rentalInfo.getCheckoutDate());
        } if (!priceData.isWeekendCharge()){
            unchargedDays += countWeekends(rentalInfo.getCheckoutDate());
        }else return rentalInfo.getRentalDays();

        return rentalInfo.getRentalDays()-unchargedDays;
    }

    private int countHolidays(String checkoutDate) {
        //There is no current library or java functionality to see if a date is a holiday
        //Will do a case switch statement because there can be only one value for the date
        int numberHolidays = 0;
        switch (checkoutDate) {
            case "8/3/15":
            case "8/4/16":
            case "8/4/17":
            case "8/4/18":
            case "8/4/19":
            case "8/3/20":
            case "8/5/21":
            case "9/7/15":
            case "9/5/16":
            case "9/4/17":
            case "9/3/18":
            case "9/2/19":
            case "9/7/20":
            case "9/6/21":
                return ++numberHolidays;
            default:
                return numberHolidays;
        }
    }

    //Might have to consider the case where it is a holiday and a weekday do not want to give to much of a discount
    private int countWeekdays(String rentalDate) throws ParseException {
        int numberWeekdays = 0;
        Date currentDate = dateFormatterUtil.stringToDateFormatter(rentalDate);
        calendar.setTime(currentDate);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return ++numberWeekdays;
            default:
                return numberWeekdays;
        }
    }

    //Since the qualified holidays never fall on a weekend this logic never crosses
    private int countWeekends(String rentalDate) throws ParseException {
        int numberWeekends = 0;
        Date currentDate = dateFormatterUtil.stringToDateFormatter(rentalDate);
        calendar.setTime(currentDate);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
            case 7:
                return ++numberWeekends;
            default:
                return numberWeekends;
        }
    }
}
