package svc.Dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RentalInfo {
    private Tool toolInfo;
    private String checkoutDate;
    private String dueDate;
    private int rentalDays;
    private int chargeDays;
    private int discountPercent;
    private double discountAmount;
    private double preTotal;
    private double postTotal;
}
