package quests;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class testQuest {
    protected String testQuestID = "testCase";
    @Test
    public void testStageCreation() {
        Quest testCase = new Quest(testQuestID);
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
        testCase.getQuestByID("4").setStatus(Quest.Status.RESOLVED);

        assertEquals(Quest.Status.NOT_ACCEPTED, testCase.getQuestByID("0").getStatus());
        assertEquals(Quest.Status.ACCEPTED, testCase.getQuestByID("1").getStatus());
        assertEquals(Quest.Status.FAILED, testCase.getQuestByID("2").getStatus());
        assertEquals(Quest.Status.COMPLETE, testCase.getQuestByID("3").getStatus());
        assertEquals(Quest.Status.RESOLVED, testCase.getQuestByID("4").getStatus());
    }
    @Test
    public void testStageStatus() {
        Quest testCase = new Quest(testQuestID);
        for (int i = 0; i<5; i++) {
            testCase.createStage(String.valueOf(i));
            testCase.getStageByID(String.valueOf(i)).setStatus(Stage.Status.FINISHED);
        }
        Stage compareStage = new Stage("Final");
        testCase.createStage("Final");

        assertEquals(5, testCase.getStageByStatus(Stage.Status.FINISHED).size());
        assertEquals(1, testCase.getStageByStatus(Stage.Status.NOT_STARTED).size());
        assertEquals(compareStage.getID(), testCase.getStageByStatus(Stage.Status.NOT_STARTED).get(0).getID());
    }
    @Test
    public void testMarkerInformation() {
        Stage testCaseOne = new Stage(testQuestID + "One");
        Stage testCaseTwo = new Stage(testQuestID + "Two");
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
        Quest testCase = new Quest(testQuestID);
        for (int i = 0; i < 5; i++) {
            testCase.createStage(testQuestID + i);
            testCase.getStageByID(testQuestID + i).setStatus(Stage.Status.FINISHED);
        }
        Stage testStage = new Stage(testQuestID + 5);
        Stage testStageTwo = testCase.getStageByID(testQuestID + "3");

        testStage.setStatus(Stage.Status.STARTED);
        testStageTwo.setStatus(Stage.Status.NOT_STARTED);
        testCase.createStage(testStage);

        ArrayList<Stage> incompleteStages = testCase.getStageByStatus(Stage.Status.NOT_STARTED);
        incompleteStages.addAll(testCase.getStageByStatus(Stage.Status.STARTED));
        for (Stage n : incompleteStages) {
            assertFalse(n.getStatus() == Stage.Status.FINISHED);
        }
        assertEquals(2, incompleteStages.size());
        assertEquals(6, testCase.getAllStages().size());
        assertEquals(testQuestID + "3", testCase.getFirstIncompleteStage().getID());
    }
    @Test
    public void testStageProgression() {
        Stage testStage = new Stage(testQuestID + "1");
        testStage.setCounter(7);
        for (int i = 0; i < 6; i++) {
            testStage.incrementCounter();
        }

        assertEquals(7, testStage.getMaxCounter());
        assertEquals(6, testStage.getProgress());
        testStage.incrementCounter();
        assertTrue(testStage.getStatus() == Stage.Status.FINISHED);
        testStage.decrementCounter();
        assertFalse(testStage.getStatus() == Stage.Status.FINISHED);
    }
}
