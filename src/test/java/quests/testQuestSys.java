package quests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testQuestSys {
    @Test
    public void testCreation() {
        QuestSys testCase = new QuestSys();
        testCase.createQuest("firstQuest");

        assertEquals(1, testCase.getListLength());
        assertEquals("firstQuest", testCase.getQuestByID("firstQuest").getID());
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
