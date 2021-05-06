package quests;

import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * An object that represents a Stage. Allows for the creation of Stages to be added to a Quest
 * object. Assigns a specified identifier and allows for the setting of relevant stage information
 * including location, description and progress.
 */
public class Stage {
    private final String stageID;
    private String desc;
    private Point2D markerXY = new Point2D.Double(0, 0);
    private int markerRad = 0;
    private boolean usesCounter = false;
    private boolean usesMarker = false;
    private int progress = 0;
    private int maxCounter = 0;

    public enum Status {
        NOT_STARTED, STARTED, FINISHED
    }
    private Status stageStatus;

    /**
     * Constructor that sets the Stage objects identifier and ensures the Status is initialised
     * as NOT_STARTED.
     * @param id The String to be used as the identifier.
     */
    public Stage(String id){
        stageID = id;
        stageStatus = Status.NOT_STARTED;
    }

    //Get Methods

    /**
     * Gets the identifier associated with this Stage.
     * @return The String that this Stage is using as it's identifier.
     */
    public String getID(){
        return stageID;
    }

    /**
     * Gets the description stored for this object, if none has been set then will return
     * a specific String.
     * @return The stored description if it has been set, specific String if null.
     */
    public String getDesc() {
        if (!usesCounter) {
            return Objects.requireNonNullElse(desc, "The stage Description has not been set");
        }
        return Objects.requireNonNullElse(desc + " (" + getProgress() + "/" + getMaxCounter() + ")",
                "The stage Description has not been set");
    }

    /**
     * Gets the location that has been set for this Stage as a Point2D (AWT).
     * @return A Point2D (AWT) representation of the set location.
     */
    public Point2D getXY() {
        return markerXY;
    }

    /**
     * Gets the X-Coordinate of this stage.
     * @return An int of the set X location. -1 if none set.
     */
    public int getX() {
        if (markerXY != null) {
            return (int) markerXY.getX();
        }
        return -1;
    }

    /**
     * Gets the Y-Coordinate of this stage.
     * @return An int of the set Y location. -1 if none set.
     */
    public int getY() {
        if (markerXY != null) {
            return (int) markerXY.getY();
        }
        return -1;
    }

    /**
     * Gets the set radius of this Stages marker. Will return 0 if Stage does not use it.
     * @return An int representing the size of the marker that should be used for this Stage.
     */
    public int getMarkerRadius() {
        return markerRad;
    }

    /**
     * Gets the current Stage Status of this object.
     * @return An Enum representation of this current Stage Status.
     */
    public Status getStatus() {
        return stageStatus;
    }

    /**
     * Gets the current progress through the Stage objectives.
     * @return An int of the amount of objectives completed. Will return -1 if Stage does not use a counter.
     */
    public int getProgress() {
        if (usesCounter) {
            return progress;
        }
        return -1;
    }

    /**
     * Gets the counter max for Stages that have multiple steps.
     * @return An int of the total steps required.
     */
    public int getMaxCounter() {
        return maxCounter;
    }

    /**
     * Gets whether or not this Stage uses a marker to indicate its location.
     * @return A boolean value, returns true if the stage does use a marker otherwise false.
     */
    public boolean checkMarker() {
        return usesMarker;
    }

    //Set Methods

    /**
     * Sets the description to be used by the Stage.
     * @param stageDescription The String value to be used as the Stage description.
     * @return The Stage object that is currently being accessed.
     */
    public Stage setDesc(String stageDescription) {
        desc = stageDescription;
        return this;
    }

    /**
     * Sets the current Status of this Stage object.
     * @param stageStatus The Enum representation of the available Stage Status to be set.
     * @return The Stage object that is currently being accessed.
     */
    public Stage setStatus(Status stageStatus){
        this.stageStatus = stageStatus;
        return this;
    }

    /**
     * Sets the current location associated with this Stage.
     * @param x An int value of the X-Coordinate for this Stage.
     * @param y An int value of the Y-Coordinate for this Stage.
     * @return The Stage object that is currently being accessed.
     */
    public Stage setXY(int x, int y) {
        markerXY = new Point2D.Double(x, y);
        return this;
    }

    /**
     * Sets the current location associated with this Stage.
     * @param location A Point2D (AWT) value of the X-Coordinate and Y-Coordinate.
     * @return The Stage object that is currently being accessed.
     */
    public Stage setXY(Point2D location) {
        markerXY = location;
        return this;
    }

    /**
     * Sets the radius to be used by the marker representing this Stages location. Also sets a
     * boolean 'usesMarker' to signify that this Stage intends for a marker to be used.
     * @param radius An int value to signify the size of the marker to be used.
     * @return The Stage object that is currently being accessed.
     */
    public Stage setMarkerRadius(int radius) {
        markerRad = radius;
        usesMarker = true;
        return this;
    }

    /**
     * Sets the Stage counter total that represent how many steps this Stage requires.
     * Also sets a boolean 'usesCounter' to signify that this Stage requires more than one
     * action.
     * @param total The total required for the Stage to be marked as FINISHED.
     * @return The Stage object that is currently being accessed.
     */
    public Stage setCounter(int total) {
        maxCounter = total;
        usesCounter = true;
        return this;
    }

    //End of Set Methods

    /**
     * Increases the counter progress until progress reaches the total required at which
     * point it will mark the Stage with the FINISHED Status.
     */
    public void incrementCounter() {
        if (usesCounter) {
            progress++;
            if (progress >= maxCounter) {
                setStatus(Status.FINISHED);
            }
        }
    }

    /**
     * Decreases the counter progress until progress reaches 0. If the Stage is marked as
     * FINISHED then it will change the Status to Started when progress reaches below the total.
     */
    public void decrementCounter() {
        if (usesCounter && progress > 0) {
            progress--;
            if (progress < maxCounter && stageStatus == Status.FINISHED) {
                setStatus(Status.STARTED);
            }
        }
    }
}
