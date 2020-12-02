import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class testQuest {
    @Test
    public void testStageCreation() {
        Quest testCase = new Quest("testCase");
        testCase.createStage("FirstStage");
        assertEquals(1, testCase.getListLength());
        assertEquals("FirstStage", testCase.getStageByID("FirstStage").getID());
    }
    @Test
    public void testStatus() {
        QuestSys testCase = new QuestSys();
        for (int i = 0; i<5; i++) {
            testCase.createQuest(String.valueOf(i));
        }
        testCase.getQuestByID("0").setStatus(Quest.Status.NOT_ACCEPTED);
        testCase.getQuestByID("1").setStatus(Quest.Status.ACCEPTED);
        testCase.getQuestByID("2").setStatus(Quest.Status.FAILED);
        testCase.getQuestByID("3").setStatus(Quest.Status.COMPLETE);
        testCase.getQuestByID("3").setStatus(Quest.Status.COMPLETE);
        testCase.getQuestByID("4").setStatus(Quest.Status.FINISHED);

        assertEquals(Quest.Status.NOT_ACCEPTED, testCase.getQuestByID("0").getStatus());
        assertEquals(Quest.Status.ACCEPTED, testCase.getQuestByID("1").getStatus());
        assertEquals(Quest.Status.FAILED, testCase.getQuestByID("2").getStatus());
        assertEquals(Quest.Status.COMPLETE, testCase.getQuestByID("3").getStatus());
        assertEquals(Quest.Status.FINISHED, testCase.getQuestByID("4").getStatus());
    }
    @Test
    public void testStageStatus() {
        Quest testCase = new Quest("testCase");
        for (int i = 0; i<5; i++) {
            testCase.createStage(String.valueOf(i));
            testCase.getStageByID(String.valueOf(i)).setStatus(true);
        }
        Stage compareStage = new Stage("Final");
        testCase.createStage("Final");

        assertEquals(5, testCase.getStageByStatus(true).size());
        assertEquals(1, testCase.getStageByStatus(false).size());
        assertEquals(compareStage.getID(), testCase.getStageByStatus(false).get(0).getID());
    }
    @Test
    public void testMarkerInformation() {
        Stage testCaseOne = new Stage("testCaseOne");
        Stage testCaseTwo = new Stage("testCaseTwo");
        Point2D testLocationOne = new Point2D.Double(50,22);
        Point2D testLocationTwo = new Point2D.Double(22, 50);
        testCaseOne.setXY(50, 22);
        testCaseOne.setMarkerRadius(5);
        testCaseTwo.setXY(testLocationTwo);

        assertEquals(testLocationOne, testCaseOne.getXY());
        assertEquals(5, testCaseOne.getMarkerRadius());
        assertEquals(testLocationTwo, testCaseTwo.getXY());
    }
    @Test
    public void testCurrentStages() {
        Quest testCase = new Quest("testQuest");
        for (int i = 0; i < 5; i++) {
            testCase.createStage("Stage" + i);
            testCase.getStageByID("Stage" + i).setStatus(true);
        }
        Stage testStage = new Stage ("Stage5");
        Stage testStageTwo = testCase.getStageByID("Stage3");

        testStage.setStatus(false);
        testStageTwo.setStatus(false);
        testCase.createStage(testStage);

        ArrayList<Stage> incompleteStages = testCase.getStageByStatus(false);
        for (Stage n : incompleteStages) {
            assertEquals(false, n.getStatus());
        }
        assertEquals(2, incompleteStages.size());
        assertEquals(6, testCase.getAllStages().size());
        assertEquals("Stage3", testCase.getFirstIncompleteStage().getID());
    }
}
