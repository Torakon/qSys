import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testQuestSys {
    @Test
    public void testCreation() {
        QuestSys testCase = new QuestSys();
        testCase.createQuest("firstQuest");
        //System.out.println("arrayLength " + testCase.getListLength());
        assertEquals(1,testCase.getListLength());
    }

}
