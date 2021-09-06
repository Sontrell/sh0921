package svc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterUtil {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

    public Date stringToDateFormatter(String rentalDateString) throws ParseException {
        return dateFormat.parse(rentalDateString);
    }

    public String dateToStringFormatter(Date rentalDate) {
        return dateFormat.format(rentalDate);
    }
}
