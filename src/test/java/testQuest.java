import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testQuest {
    @Test
    public void testStageCreation() {
        Quest testCase = new Quest("testCase");
        testCase.addStage("FirstStage");
        assertEquals(1,testCase.getListLength());
        assertEquals("FirstStage", testCase.getStageByID("FirstStage").getID());
    }
    @Test
    public void testStatus() {
        QuestSys testCase = new QuestSys();
        for (int i = 0; i<4; i++) {
            testCase.createQuest(String.valueOf(i));
        }
        testCase.getQuestByID("0").setStatus(Quest.Status.NOT_ACCEPTED);
        testCase.getQuestByID("1").setStatus(Quest.Status.ACCEPTED);
        testCase.getQuestByID("2").setStatus(Quest.Status.FAILED);
        testCase.getQuestByID("3").setStatus(Quest.Status.COMPLETE);

        assertEquals(Quest.Status.NOT_ACCEPTED,testCase.getQuestByID("0").getStatus());
        assertEquals(Quest.Status.ACCEPTED,testCase.getQuestByID("1").getStatus());
        assertEquals(Quest.Status.FAILED,testCase.getQuestByID("2").getStatus());
        assertEquals(Quest.Status.COMPLETE,testCase.getQuestByID("3").getStatus());
    }
}
