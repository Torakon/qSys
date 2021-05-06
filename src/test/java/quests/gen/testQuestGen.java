package quests.gen;

import quests.Quest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class testQuestGen {
    @Test
    public void testGeneration() {
        QuestGen gen = new QuestGen();
        gen.toggleTypeParams(QuestGen.Type.TYPE_KILL);
        assertNotNull(gen.genQuest("testNotNull"));
    }

    @Test
    public void testMultipleType() {
        QuestGen gen = new QuestGen();
        ArrayList<Quest> testQuests = new ArrayList<>();

        gen.toggleTypeParams(QuestGen.Type.TYPE_KILL);
        gen.toggleTypeParams(QuestGen.Type.TYPE_MOVE);

        for (int i = 0; i < 15; i++) {
            testQuests.add(gen.genQuest("test" + i));
        }
        int killQuests = 0;
        int moveQuests = 0;
        for (Quest q : testQuests) {
            if (q.getFirstIncompleteStage().getID().startsWith("killAt")) {
                killQuests++;
            } else if (q.getFirstIncompleteStage().getID().startsWith("moveTo")) {
                moveQuests++;
            }
        }
        assertTrue(killQuests > 0);
        assertTrue(moveQuests > 0);
    }

    @Test
    public void testLocationBounds() {
        QuestGen gen = new QuestGen();

        gen.toggleTypeParams(QuestGen.Type.TYPE_KILL);
        gen.setLocationBounds(100, 100, 110, 110, false);

        for (int i = 0; i < 20; i++) {
            Quest generated = gen.genQuest("generatedQuest");
            int x = generated.getFirstIncompleteStage().getX();
            int y = generated.getFirstIncompleteStage().getY();

            assertTrue(x >= 100 && x <= 110);
            assertTrue(y >= 100 && y <= 110);
        }

        gen.setLocation(250, 300, false);
        Quest specLocation = gen.genQuest("generatedQuest");
        int x = specLocation.getFirstIncompleteStage().getX();
        int y = specLocation.getFirstIncompleteStage().getY();

        assertEquals(x, 250);
        assertEquals(y, 300);
    }

    @Test
    public void testCorrectMarker() {
        QuestGen gen = new QuestGen();

        gen.toggleTypeParams(QuestGen.Type.TYPE_KILL);
        gen.setLocation(300, 350, true);
        gen.setMarkerRadius(10, 20);

        for (int i = 0; i < 20; i++) {
            Quest generated = gen.genQuest("generated");
            int mRadius = generated.getFirstIncompleteStage().getMarkerRadius();

            assertTrue(mRadius >= 10 && mRadius <= 20);
            System.out.println("Generated radius is: " + mRadius);
        }

        gen.setMarkerRadius(20);
        Quest specRadius = gen.genQuest("generated");
        int mRadius = specRadius.getFirstIncompleteStage().getMarkerRadius();

        assertEquals(mRadius, 20);
    }

    @Test
    public void testCounterTotal() {
        QuestGen gen = new QuestGen();
        gen.toggleTypeParams(QuestGen.Type.TYPE_KILL);
        gen.setCounterBounds(4, 10);

        for (int i = 0; i < 20; i++) {
            Quest generated = gen.genQuest("generated");
            int counter = generated.getFirstIncompleteStage().getMaxCounter();

            assertTrue(counter >= 4 && counter <= 10);
            System.out.println("Generated counter is: " + counter);
        }

        gen.setCounterBounds(4,4);
        Quest specCounter = gen.genQuest("generated");
        assertEquals(specCounter.getFirstIncompleteStage().getMaxCounter(), 4);
    }

    @Test
    public void testMultipleStages() {
        QuestGen gen = new QuestGen();
        gen.toggleTypeParams(QuestGen.Type.TYPE_KILL);
        gen.setCounterBounds(4, 10);
        gen.setStageCount(5, true);

        for(int i = 0; i < 20; i++) {
            Quest generated = gen.genQuest("generated");
            int stageCount = generated.getListLength() - 1;

            assertTrue(stageCount >= 0 && stageCount <= 5);
            System.out.println("Generated stages: " + stageCount);
        }
    }
}
