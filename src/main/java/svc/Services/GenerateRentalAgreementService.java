package svc.Services;

import svc.Dtos.RentalInfo;

import java.text.DecimalFormat;

/*
This Class is to Generate the Rental Agreement for the user though print statements through the console
 */
public class GenerateRentalAgreementService {
    public void generateAgreement(RentalInfo rentalInfo) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        //Print Tool info
        System.out.println("Tool Code : " + rentalInfo.getToolInfo().getCode());
        System.out.println("Tool Type : " + rentalInfo.getToolInfo().getType());
        System.out.println("Tool Brand : " + rentalInfo.getToolInfo().getBrand());

        //Print Rental Days Info
        System.out.println("Rental Day Count : " + rentalInfo.getRentalDays());
        System.out.println("Checkout Date : " + rentalInfo.getCheckoutDate());
        System.out.println("Due Date : " + rentalInfo.getDueDate());
        System.out.println("Rental Day Charge : " + rentalInfo.getToolInfo().getPrice().getDailyPrice());
        System.out.println("Charge Days : " + rentalInfo.getChargeDays());

        //Print Charge Info
        System.out.println("Pre-Discount Charge : $" + decimalFormat.format(rentalInfo.getPreTotal()));
        System.out.println("Discount Percent : " + rentalInfo.getDiscountPercent() + "%");
        System.out.println("Discount Amount : $" + decimalFormat.format(rentalInfo.getDiscountAmount()));
        System.out.println("Final Charge : $" + decimalFormat.format(rentalInfo.getPostTotal()));
    }
}
