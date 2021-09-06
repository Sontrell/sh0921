package svc.Services;

import svc.Dtos.RentalInfo;

public class CheckoutTotalService {
    public void calculateChargeTotal(RentalInfo rentalInfo) {
        rentalInfo.setPreTotal(rentalInfo.getChargeDays() * rentalInfo.getToolInfo().getPrice().getDailyPrice());
        rentalInfo.setDiscountAmount((rentalInfo.getPreTotal() * rentalInfo.getDiscountPercent()) / 100);
        rentalInfo.setPostTotal(rentalInfo.getPreTotal() - rentalInfo.getDiscountAmount());
    }
}
