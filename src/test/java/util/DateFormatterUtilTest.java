package util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import svc.utils.DateFormatterUtil;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateFormatterUtilTest {

    private static DateFormatterUtil formatterUtil;
    private static Date date;

    @BeforeAll
    public static void setUp() {
        formatterUtil = new DateFormatterUtil();
        date = new Date();
    }

    @Test
    public void testDateToStringFormatter_ValidDate_ReturnsString() {
        assertNotNull("returned value was null", formatterUtil.dateToStringFormatter(date));
    }

    @Test
    public void testStringToDateFormatter_ValidDate_ReturnsString() throws ParseException {
        assertNotNull("returned value was null", formatterUtil.stringToDateFormatter("09/22/2021").toString());
    }

    @Test
    public void testDateToStringFormatter_NullDate_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            formatterUtil.dateToStringFormatter(null);
        });
    }

}
