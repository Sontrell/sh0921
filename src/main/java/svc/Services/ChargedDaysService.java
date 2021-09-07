package svc.Services;

import svc.Dtos.Prices;
import svc.Dtos.RentalInfo;
import svc.utils.DateFormatterUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

//What if the input includes 0 in the format
public class ChargedDaysService {
    private DateFormatterUtil dateFormatterUtil = new DateFormatterUtil();
    private Calendar calendar = Calendar.getInstance();

    public int calculateChargeDays(RentalInfo rentalInfo) throws ParseException {
        int unchargedDays = 0;
        int rentalDays = rentalInfo.getRentalDays();
        Prices priceData = rentalInfo.getToolInfo().getPrice();

        //If there was input error and O days was entered stop here and do no further logic
        //If it had continued there is potential to have a negative amount of charged days
        if (rentalDays == 0) return 0;

        if(!priceData.isHolidayCharge()){
            unchargedDays += countHolidays(rentalInfo.getCheckoutDate());
        }
        if (!priceData.isWeekdayCharge()){
            //All options are set to true right now coding for the potential of being false at some point
            //Add a subtraction for any holidays counted and assign to new field
            unchargedDays += countWeekdays(rentalInfo.getCheckoutDate(), rentalDays);
        }
        if (!priceData.isWeekendCharge()){
            unchargedDays += countWeekends(rentalInfo.getCheckoutDate(), rentalDays);
        }
        return rentalInfo.getRentalDays()-unchargedDays;
    }

    private int countHolidays(String checkoutDate) {
        //There is no current library or java functionality to see if a date is a holiday
        //Will do a case switch statement because there can be only one value for the date
        int numberHolidays = 0;
        switch (checkoutDate) {
            //Dates without using 0
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
            //Dates with using 0
            case "08/03/15":
            case "08/04/16":
            case "08/04/17":
            case "08/04/18":
            case "08/04/19":
            case "08/03/20":
            case "08/05/21":
            case "09/07/15":
            case "09/05/16":
            case "09/04/17":
            case "09/03/18":
            case "09/02/19":
            case "09/07/20":
            case "09/06/21":
                return ++numberHolidays;
            default:
                return numberHolidays;
        }
    }

    //Might have to consider the case where it is a holiday and a weekday do not want to give to much of a discount
    private int countWeekdays(String rentalDate, int rentalDays) throws ParseException {
        int numberWeekdays = 0;
        Date currentDate = dateFormatterUtil.stringToDateFormatter(rentalDate);
        calendar.setTime(currentDate);

        //Changed from case switch to if statements because it has to add for each day
        // Need to consider all days not just the checkout date and the return date
        for(int i = 0; i < rentalDays; i++) {
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    ++numberWeekdays;
                    break;
                default:
                    break;
            }
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
        return numberWeekdays;
    }

    //Since the qualified holidays never fall on a weekend this logic never crosses
    private int countWeekends(String rentalDate, int rentalDays) throws ParseException {
        int numberWeekends = 0;
        Date currentDate = dateFormatterUtil.stringToDateFormatter(rentalDate);
        calendar.setTime(currentDate);

        //Changed from case switch to if statements because it has to add for each day
        // Need to consider all days not just the checkout date and the return date
        for(int i = 0; i < rentalDays; i++) {
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case 1:
                case 7:
                    ++numberWeekends;
                    break;
                default:
                    break;
            }
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
        return numberWeekends;
    }
}
