package svc.Dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Prices {
    private double dailyPrice;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;
}
