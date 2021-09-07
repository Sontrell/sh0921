package svc.Data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import svc.Dtos.Tool;

import static org.junit.Assert.assertEquals;

public class ToolsMapTest {

    private static ToolsMap toolsMap;

    @BeforeAll
    public static void setUp() {
        toolsMap = new ToolsMap();
    }

    @Test
    public void testToolsMap_AllValuesArePresent() {
        assertEquals("Tools Map size needs to have 4 values",toolsMap.toolsMap().size(), 4);
    }

    @Test
    public void testToolsMap_LadderKeyHasCorrectValues() {
        Tool ladderTool = toolsMap.toolsMap().get("LADW");
        assertEquals(ladderTool.getBrand(), "Werner");
        assertEquals(ladderTool.getCode(), "LADW");
        assertEquals(ladderTool.getType(), "Ladder");
        assertEquals(ladderTool.getPrice().getDailyPrice(), 1.99, 0);
        assertEquals(ladderTool.getPrice().isHolidayCharge(), false);
        assertEquals(ladderTool.getPrice().isWeekdayCharge(), true);
        assertEquals(ladderTool.getPrice().isWeekendCharge(), true);
    }

    @Test
    public void testToolsMap_ChainsawKeyHasCorrectValues() {
        Tool ladderTool = toolsMap.toolsMap().get("CHNS");
        assertEquals(ladderTool.getBrand(), "Stihl");
        assertEquals(ladderTool.getCode(), "CHNS");
        assertEquals(ladderTool.getType(), "Chainsaw");
        assertEquals(ladderTool.getPrice().getDailyPrice(), 1.49, 0);
        assertEquals(ladderTool.getPrice().isHolidayCharge(), true);
        assertEquals(ladderTool.getPrice().isWeekdayCharge(), true);
        assertEquals(ladderTool.getPrice().isWeekendCharge(), false);
    }

    @Test
    public void testToolsMap_JackHammerRKeyHasCorrectValues() {
        Tool ladderTool = toolsMap.toolsMap().get("JAKR");
        assertEquals(ladderTool.getBrand(), "Ridgid");
        assertEquals(ladderTool.getCode(), "JAKR");
        assertEquals(ladderTool.getType(), "Jackhammer");
        assertEquals(ladderTool.getPrice().getDailyPrice(), 2.99, 0);
        assertEquals(ladderTool.getPrice().isHolidayCharge(), false);
        assertEquals(ladderTool.getPrice().isWeekdayCharge(), true);
        assertEquals(ladderTool.getPrice().isWeekendCharge(), false);
    }

    @Test
    public void testToolsMap_JackHammerDKeyHasCorrectValues() {
        Tool ladderTool = toolsMap.toolsMap().get("JAKD");
        assertEquals(ladderTool.getBrand(), "DeWalt");
        assertEquals(ladderTool.getCode(), "JAKD");
        assertEquals(ladderTool.getType(), "Jackhammer");
        assertEquals(ladderTool.getPrice().getDailyPrice(), 2.99, 0);
        assertEquals(ladderTool.getPrice().isHolidayCharge(), false);
        assertEquals(ladderTool.getPrice().isWeekdayCharge(), true);
        assertEquals(ladderTool.getPrice().isWeekendCharge(), false);
    }
}
