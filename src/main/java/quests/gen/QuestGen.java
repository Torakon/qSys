package quests.gen;

import quests.Quest;
import quests.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Generates a random quest based on input from the game code and interacts with the Quest
 * System to add the Quest object to be tracked. Currently supports the creation of MOVE
 * and KILL quests with other types able to be added on. Allows for the developer to define
 * certain restrictions such as certain Quest types, location bounds and max required counter.
 */
public class QuestGen  {
    private int xMax = 0;
    private int yMax = 0;
    private int xMin = 0;
    private int yMin = 0;
    private int counterMin = 0;
    private int counterMax = 1;
    private int radiusMin = 15;
    private int radiusMax = 20;
    private int maxStages = 1;
    private boolean usesMarker = false;
    private boolean mixedStages = false;
    int idAdd = 0;

    public enum Type {
        TYPE_KILL, TYPE_FETCH, TYPE_MOVE
    }
    private TypeDef<Type> typeTrack = new TypeDef<Type>(3);

    /**
     * Constructor that puts the different Quest types available into a custom Data
     * Structure (TypeDef) that is used to determine which Quest types to use when
     * generating a quest.
     */
    public QuestGen() {
        typeTrack.put(Type.TYPE_KILL);
        typeTrack.put(Type.TYPE_FETCH);
        typeTrack.put(Type.TYPE_MOVE);
    }

    /**
     * Toggles whether of not the generation of a certain Quest type is allowed.
     * @param toAdd An Enum representation of a Quest Type.
     */
    public void toggleTypeParams(Type toAdd) {
        switch (toAdd){
            case TYPE_KILL:
                typeTrack.toggle(Type.TYPE_KILL);
                break;
            case TYPE_FETCH:
                typeTrack.toggle(Type.TYPE_FETCH);
                break;
            case TYPE_MOVE:
                typeTrack.toggle(Type.TYPE_MOVE);
                break;
            default:
                break;
        }
    }

    /**
     * Sets the location boundaries to be used when generating a Quest.
     * @param xMin An int value representing the minimum X-Coordinate.
     * @param yMin An int value representing the minimum Y-Coordinate.
     * @param xMax An int value representing the maximum X-Coordinate.
     * @param yMax An int value representing the maximum Y-Coordinate.
     * @param usesMarker Should the generated quest use a marker to represent it's location
     */
    public void setLocationBounds(int xMin, int yMin, int xMax, int yMax, boolean usesMarker) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax + 1;
        this.yMax = yMax + 1;
        this.usesMarker = usesMarker;
    }

    /**
     * Sets a specific location for any generated Quest.
     * @param x An int value representing the desired X-Coordinate.
     * @param y An int value representing the desired Y-Coordinate.
     * @param usesMarker Should the generated quest use a marker to represent it's location.
     */
    public void setLocation(int x, int y, boolean usesMarker) {
        setLocationBounds(x, y, x, y, usesMarker);
    }

    /**
     * Sets the boundaries for the amount of steps to be used when creating a Stage for the
     * generated Quest.
     * @param counterMin The smallest amount of steps to mark a Stage as FINISHED.
     * @param counterMax The largest amount of steps to mark a Stage as FINISHED.
     */
    public void setCounterBounds(int counterMin, int counterMax) {
        this.counterMax = counterMax;
        this.counterMin = counterMin;
    }

    /**
     * Sets the maximum amount of Stages per generated Quest. Also sets whether or not Stages
     * can be of different types from each other.
     * @param maxStages An int value for the most amount of Stages permitted.
     * @param mixedStages boolean value designating whether stages can be of different types.
     */
    public void setStageCount(int maxStages, boolean mixedStages) {
        if (maxStages > 1) {
            this.maxStages = maxStages;
        } else {
            this.maxStages = 1;
        }
        this.mixedStages = mixedStages;
    }

    /**
     * Sets the bounds for the size of the marker. Will not change if the maximum is provided
     * smaller than the supplied minimum.
     * @param radiusMin The integer to be used for the smallest possible marker size.
     * @param radiusMax The integer to be used for the largest possible marker size.
     */
    public void setMarkerRadius(int radiusMin, int radiusMax) {
        if ((radiusMin > 0 && radiusMax > 0) && radiusMin <= radiusMax) {
            this.radiusMin = radiusMin;
            this.radiusMax = radiusMax;
        }
    }

    /**
     * Sets the bounds for the size of the marker to be a specific value.
     * @param markerRadius The integer to be used for the exact size of the marker radius.
     */
    public void setMarkerRadius(int markerRadius) {
        setMarkerRadius(markerRadius, markerRadius);
    }

    /**
     * Generates a Quest object adhering to variables set by the developer using pseudorandom
     * numbers to determine the exact Quest variables from the supplied bounds.
     * @param questID A String value to be used for the generated Quests identifier.
     * @return A Quest object that can then be added to the games Quest System instance.
     */
    public Quest genQuest(String questID) {
        Quest genQ = new Quest(questID);
        ArrayList<Type> availableTypes = typeTrack.getAllTrue();
        Random rand = new Random();
        int getRandom = rand.nextInt(availableTypes.size());
        Type selectedType = availableTypes.get(getRandom);
        int randSize = rand.nextInt(maxStages) + 1;
        for (int i = 0; i < randSize; i++) {
            switch (selectedType) {
                case TYPE_KILL:
                    genQ.createStage(createKill());
                    genQ.setDesc("Eliminate your Opposition");
                    break;
                case TYPE_FETCH:
                    //createFetch();
                    //genQ.setDesc("Retrieve an item");
                    break;
                case TYPE_MOVE:
                    genQ.createStage(createMove());
                    genQ.setDesc("Go to a Location");
                    break;
                default:
                    break;
            }
            idAdd++;
            if (mixedStages) {
                getRandom = rand.nextInt(availableTypes.size());
                selectedType = availableTypes.get(getRandom);
                if (i > 0) {
                    genQ.setDesc("Radiant Quest");
                }
            }
        }
        Stage returnToGiver = new Stage ("return");
        returnToGiver.setDesc("Turn in the Quest.");
        genQ.createStage(returnToGiver);

        return genQ;
    }

    /**
     * Generates a Stage with settings requiring a Player to kill a certain amount of 'enemies'.
     * @return A Stage object to be added to the generated Quest.
     */
    private Stage createKill() {
        Stage genS = new Stage("killAt" + idAdd);
        Random rand = new Random();

        if(xMax != 0 && yMax != 0) {
            genS.setXY(rand.nextInt(xMax - xMin) + xMin, rand.nextInt(yMax - yMin) + yMin);
        }
        if (counterMax != 0) {
            genS.setCounter(rand.nextInt((counterMax + 1) - counterMin) + counterMin);
        }
        if (usesMarker) {
            if (radiusMin != radiusMax) {
                genS.setMarkerRadius(rand.nextInt((radiusMax) - radiusMin) + radiusMin);
            } else {
                genS.setMarkerRadius(radiusMin);
            }
        }
        genS.setDesc("Kill enough Enemies");
        return genS;
    }

    /**
     * Generates a Stage with settings requiring a Player to move to a location.
     * @return A Stage object to be added to the generated Quest.
     */
    private Stage createMove() {
        Stage genS = new Stage("moveTo" + idAdd);
        Random rand = new Random();

        if (xMax != 0 && yMax != 0) {
            genS.setXY(rand.nextInt(xMax - xMin) + xMin, rand.nextInt(yMax - yMin) + yMin);
        } else {
            genS.setXY(0,0);
        }
        if (usesMarker) {
            if (radiusMin != radiusMax) {
                genS.setMarkerRadius(rand.nextInt((radiusMax + 1) - radiusMin) + radiusMin);
            } else {
                genS.setMarkerRadius(radiusMin);
            }
        }
        genS.setDesc("Move to the marker.");

        return genS;
    }

}
