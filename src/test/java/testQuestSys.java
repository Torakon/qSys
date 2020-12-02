import org.junit.Test;
import static org.junit.Assert.*;

public class testQuestSys {
    @Test
    public void testCreation() {
        QuestSys testCase = new QuestSys();
        testCase.createQuest("firstQuest");

        assertEquals(1, testCase.getListLength());
        assertEquals("firstQuest", testCase.getQuestID(0));
    }
    @Test
    public void testGetByStage() {
        QuestSys testCase = new QuestSys();
        for (int i = 0; i<8; i++) {
            testCase.createQuest(String.valueOf(i));
        }
        testCase.getQuestByID("1").setStatus(Quest.Status.ACCEPTED);
        testCase.getQuestByID("3").setStatus(Quest.Status.ACCEPTED);
        for (int i = 4; i<8; i++){
            testCase.getQuestByID(String.valueOf(i)).setStatus(Quest.Status.COMPLETE);
        }

        assertEquals(2, testCase.getQuestByStatus(Quest.Status.ACCEPTED).size());
        assertEquals(4, testCase.getQuestByStatus(Quest.Status.COMPLETE).size());
    }
}
