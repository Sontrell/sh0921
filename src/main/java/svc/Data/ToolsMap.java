package svc.Data;

import lombok.*;
import svc.Dtos.Prices;
import svc.Dtos.Tool;

import java.util.HashMap;
import java.util.Map;

/*
This class will hold the data for all the tool information
The key value will be the ToolCode and the value will be of type Tool
This potentially could be moved to a Database
 */
@Data
public class ToolsMap {
    public Map<String, Tool> toolsMap () {

        Map<String, Tool> toolMap = new HashMap<String, Tool>();

        //Mapping for Ladder
        toolMap.put(ToolsCodes.LADDER_CODE,
                Tool.builder().price(
                        Prices.builder()
                                .dailyPrice(1.99)
                                .weekdayCharge(true)
                                .weekendCharge(true)
                                .holidayCharge(false)
                                .build())
                        .type("Ladder")
                        .brand("Werner")
                        .code(ToolsCodes.LADDER_CODE) //May be redundant and could have a better way to implement
                        .build());

        //Mapping for Chainsaw
        toolMap.put(ToolsCodes.CHAINSAW_CODE,
                Tool.builder().price(
                                Prices.builder()
                                        .dailyPrice(1.49)
                                        .weekdayCharge(true)
                                        .weekendCharge(false)
                                        .holidayCharge(true)
                                        .build())
                        .type("Chainsaw")
                        .brand("Stihl")
                        .code(ToolsCodes.CHAINSAW_CODE) //May be redundant and could have a better way to implement
                        .build());

        //Mapping for JackHammerR
        toolMap.put(ToolsCodes.JACKHAMMER_R_CODE,
                Tool.builder().price(getJackHammerPrices())
                        .type("Jackhammer")
                        .brand("Ridgid")
                        .code(ToolsCodes.JACKHAMMER_R_CODE) //May be redundant and could have a better way to implement
                        .build());

        //Mapping for JackHammerD
        toolMap.put(ToolsCodes.JACKHAMMER_D_CODE,
                Tool.builder().price(getJackHammerPrices())
                        .type("Jackhammer")
                        .brand("DeWalt")
                        .code(ToolsCodes.JACKHAMMER_D_CODE) //May be redundant and could have a better way to implement
                        .build());

        return toolMap;
    }

    private Prices getJackHammerPrices() {
        return Prices.builder()
                .dailyPrice(2.99)
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(false)
                .build();
    }
}
