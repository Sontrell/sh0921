package svc.Services;

import svc.Dtos.RentalInfo;
import svc.utils.DateFormatterUtil;

import java.util.Calendar;
import java.util.Date;

public class ReturnDateService {
    DateFormatterUtil dateFormatterUtil = new DateFormatterUtil();

    public RentalInfo calculate(RentalInfo rentalInfo) throws Exception {
        //Keep separation between manipulated data and original
        RentalInfo updatedRentalInfo = rentalInfo;

        if (rentalInfo.getCheckoutDate() != null && rentalInfo.getRentalDays() != 0) {
            Date rentalDate = dateFormatterUtil.stringToDateFormatter(rentalInfo.getCheckoutDate());
            updatedRentalInfo.setDueDate(calculateReturnDate(rentalDate, rentalInfo.getRentalDays()));
            return updatedRentalInfo;
        }else throw new Exception("The value of the checkout date was null or number of rental days not valid");
    }

    public String calculateReturnDate(Date rentalDate, int rentalDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rentalDate);
        calendar.add(Calendar.DAY_OF_WEEK, rentalDays);
        return dateFormatterUtil.dateToStringFormatter(calendar.getTime());
    }
}
