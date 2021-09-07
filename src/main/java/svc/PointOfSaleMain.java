package svc;

import svc.Data.ToolsMap;
import svc.Dtos.Prices;
import svc.Dtos.RentalInfo;
import svc.Dtos.Tool;
import svc.Services.*;

public class PointOfSaleMain {
    public static void main(String[] args) throws Exception {
        StartApplication startApplication = new StartApplication();
        startApplication.startApplication(args);
    }
}
